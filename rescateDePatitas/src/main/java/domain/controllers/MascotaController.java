package domain.controllers;

import com.sun.org.apache.xpath.internal.operations.Or;
import domain.models.entities.mascotas.*;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.personas.Usuario;
import domain.models.entities.publicaciones.GestorDePublicaciones;
import domain.models.entities.publicaciones.Pregunta;
import domain.models.entities.rol.Duenio;
import domain.models.repositories.*;
import domain.models.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MascotaController {
    private RepositorioGenerico<Mascota> repo;

    public MascotaController() {
        this.repo = FactoryRepositorio.get(Mascota.class);
    }


//    //TODO  Es seguro borrar?
//    public ModelAndView mostrarPerdidas(Request request, Response response) {
//        Map<String, Object> parametros = new HashMap<>();
//        List<Mascota> mascotas = this.repo.buscarTodos();
//        parametros.put("mascotas", mascotas);
//        return new ModelAndView(parametros, "perdidas.hbs");
//    }
//
//    public ModelAndView mostrarEncontradas(Request request, Response response) {
//        Map<String, Object> parametros = new HashMap<>();
//        List<Mascota> mascotas = this.repo.buscarTodos();
//        parametros.put("mascotas", mascotas);
//        return new ModelAndView(parametros, "encontradas.hbs");
//    }

    public ModelAndView registroMascota(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<TipoDeDocumento> tipo = new ArrayList<>();
        tipo.add(TipoDeDocumento.valueOf("DNI"));
        tipo.add(TipoDeDocumento.valueOf("LIBRETA_CIVICA"));
        tipo.add(TipoDeDocumento.valueOf("PASAPORTE"));
        tipo.add(TipoDeDocumento.valueOf("CEDULA"));
        tipo.add(TipoDeDocumento.valueOf("LIBRETA_ENROLAMIENTO"));

        List<String> provincias = new ArrayList<>();
        provincias.add("Buenos Aires");
        provincias.add("CABA");
        provincias.add("Córdoba");
        provincias.add("Santa Fe");

        List<Organizacion> organizaciones = new ArrayList<>();

        OrganizacionController cOrg = OrganizacionController.getInstancia();
        RepositorioGenerico<Organizacion> repoOrg = cOrg.getRepositorio();
        organizaciones = repoOrg.buscarTodos();

        parametros.put("organizaciones", organizaciones);

        UsuarioController usuarioController = UsuarioController.getInstancia();
        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);

        PreguntasController cPreguntas = PreguntasController.getInstancia();
        RepositorioDePreguntas repoPreguntas = cPreguntas.getRepositorio();
        List<Pregunta> generales = repoPreguntas.buscarPorTipo("general");

        parametros.put("tipos", tipo);
        parametros.put("generales", generales);
        parametros.put("provincias", provincias);

        return new ModelAndView(parametros, "registro_mascota.hbs");
    }

    public Response guardarMascota(Request request, Response response) {
        Mascota mascota = new Mascota();
        asignarAtributosA(mascota, request);

        if (request.session().attribute("id") != null) {
            RepositorioDePersonas repoPersonas = RepositorioDePersonas.getInstancia();
            Persona duenio = repoPersonas.dameLaPersona(request.session().attribute("id"));
            mascota.setPersona(duenio);
        } else {
            PersonaController cPersona = PersonaController.getInstancia();
            RepositorioDePersonas repoPersona = cPersona.getRepositorio();
            String cadena = request.queryParams("fnacPersona") + request.queryParams("nroDoc");
            String hashPersona = org.apache.commons.codec.digest.DigestUtils.md5Hex(cadena);
            Persona personaEncontrada = repoPersona.buscarPersona(hashPersona);

            if (personaEncontrada != null) {
                mascota.setPersona(personaEncontrada);
            } else {
                Persona persona = new Persona();
                asignarAtributosA(persona, request);
                persona.setUsuarioTemporal(hashPersona);
                mascota.setPersona(persona);
                mascota.setDuenio((Duenio)persona.getRolElegido());
                repoPersona.agregar(persona);
            }
        }


        this.repo.agregar(mascota);

        response.redirect("/ok");

        return response;
    }

    private void asignarAtributosA(Mascota mascota, Request request) {
        if (request.queryParams("nombre") != null) {
            mascota.setNombre(request.queryParams("nombre"));
        }

        if (request.queryParams("apodo") != null) {
            mascota.setApodo(request.queryParams("apodo"));
        }

        if (request.queryParams("especie") != null) {
            mascota.setEspecie(request.queryParams("especie"));
        }

        if (request.queryParams("sexo") != null) {
            mascota.setGenero(request.queryParams("sexo"));
        }

        if (request.queryParams("tamanio") != null) {
            mascota.setTamanio(request.queryParams("tamanio"));
        }

        if (request.queryParams("descripcion") != null) {
            mascota.setDescripcion("descripcion");
        }

        if (request.queryParams("edad") != null) {
            int edad = Integer.valueOf(request.queryParams("edad"));
            mascota.setEdad(edad);
        }

        if (request.queryParams("foto") != null) {
            //TODO hacerlo para muchas fotos
            List<Foto> fotos = new ArrayList<>();
            Foto foto = new Foto();
            foto.setURLfoto(request.queryParams("foto"));
            fotos.add(foto);
            mascota.setFotos(fotos);
        }

        if (request.queryParams("asociacion") != null) {
            String nombreAsociacion = request.queryParams("asociacion");
            OrganizacionController cOrg = OrganizacionController.getInstancia();
            RepositorioDeOrganizaciones repoOrg = cOrg.getRepositorio();
            Organizacion organizacion = repoOrg.buscarPorNombre(nombreAsociacion);
            mascota.setOrganizacion(organizacion);

        }

        PreguntasController cPreguntas = PreguntasController.getInstancia();
        RepositorioDePreguntas repoPreguntas = cPreguntas.getRepositorio();
        List<Pregunta> generales = repoPreguntas.buscarPorTipo("general");
        List<CaracteristicaConRta> elegidas = new ArrayList<>();
        for (Pregunta pregunta : generales) {
            if (request.queryParams(pregunta.getPregunta()) != null) {
                String pregunta_elegida = pregunta.getPregunta();
                String respuesta_elegida = request.queryParams(pregunta.getPregunta());

                CaracteristicaConRta caracteristicaConRta = new CaracteristicaConRta(pregunta_elegida, respuesta_elegida);
                caracteristicaConRta.setMascota(mascota);
                elegidas.add(caracteristicaConRta);
            }
        }
        mascota.setCaracteristicas(elegidas);

    }

    private void asignarAtributosA(Persona persona, Request request) {
        if (request.queryParams("nombre") != null) {
            persona.setNombre(request.queryParams("nombrePersona"));
        }

        if (request.queryParams("apellido") != null) {
            persona.setApellido(request.queryParams("apellido"));
        }

        if (request.queryParams("fnacPersona") != null) {
            persona.setFechaDeNacimiento(LocalDate.parse(request.queryParams("fnacPersona")));
        }

        if (request.queryParams("tipoDoc") != null) {
            persona.setTipoDoc(TipoDeDocumento.valueOf(request.queryParams("tipoDoc")));
        }

        if (request.queryParams("nroDoc") != null) {
            persona.setNroDoc(Integer.valueOf(request.queryParams("nroDoc")));
        }
        String pais = "";
        String provincia = "";
        String direccion = "";
        if (request.queryParams("provincia") != null) {
            provincia = request.queryParams("provincia");
        }

        if (request.queryParams("pais") != null) {
            pais = request.queryParams("pais");
        }

        if (request.queryParams("direccion") != null) {
            direccion = request.queryParams("direccion");
        }
        persona.setDireccion(direccion + "," + provincia + "," + pais);

        String cNombre = "";
        String cApellido = "";
        String cCorreo = "";
        String cNumero = "";
        Estrategia medioPreferido = Estrategia.valueOf("WHATSAPP");

        if (request.queryParams("cNombre") != null) {
            cNombre = request.queryParams("cNombre");
        }

        if (request.queryParams("cApellido") != null) {
            cApellido = request.queryParams("cApellido");
        }

        if (request.queryParams("cNumero") != null) {
            cNumero = request.queryParams("cNumero");
        }

        if (request.queryParams("cCorreo") != null) {
            cCorreo = request.queryParams("cCorreo");
        }

        if (request.queryParams("medioPreferido") != null) {
            if (request.queryParams("medioPreferido").equals("Email")) {
                medioPreferido = Estrategia.valueOf("EMAIL");
            } else {
                if (request.queryParams("medioPreferido").equals("WhatsApp")) {
                    medioPreferido = Estrategia.valueOf("WHATSAPP");
                } else medioPreferido = Estrategia.valueOf("SMS");
            }
        }

        Contacto contacto = new Contacto(cNombre, cApellido, cNumero, cCorreo, medioPreferido);
        contacto.setPersona(persona);

        List<Contacto> contactos = new ArrayList<>();
        contactos.add(contacto);

        persona.setContactos(contactos);
    }

    public ModelAndView creada(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "ok.hbs");
    }

    public ModelAndView registroMascotaAsoc(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Organizacion> organizaciones = new ArrayList<>();

        OrganizacionController cOrg = OrganizacionController.getInstancia();
        RepositorioGenerico<Organizacion> repoOrg = cOrg.getRepositorio();
        organizaciones = repoOrg.buscarTodos();

        parametros.put("organizaciones", organizaciones);
        return new ModelAndView(parametros, "registro_mascota_asociacion.hbs");
    }

    public Response guardarAsociacion(Request request, Response response) {
        Organizacion organizacion;
        String nombre;

        if (request.queryParams("asociacion") != null) {
            nombre = request.queryParams("asociacion");
            OrganizacionController cOrg = OrganizacionController.getInstancia();
            RepositorioDeOrganizaciones repoOrg = cOrg.getRepositorio();
            organizacion = repoOrg.buscarPorNombre(nombre);

            if (request.session().attribute("id") != null) {
                RepositorioDePersonas repoPersonas = RepositorioDePersonas.getInstancia();
                Persona duenio = repoPersonas.dameLaPersona(request.session().attribute("id"));
                List<Mascota> mascotas = duenio.getMascotas();
                Mascota mascota = mascotas.get(mascotas.size() - 1);
                mascota.setOrganizacion(organizacion);
                this.repo.modificar(mascota);
            }

        }

        response.redirect("/ok");
        return response;

    }

    public ModelAndView darEnAdopcion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "dar_adopcion.hbs");
    }
}
