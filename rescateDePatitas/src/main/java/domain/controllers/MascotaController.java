package domain.controllers;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.personas.Usuario;
import domain.models.entities.publicaciones.Pregunta;
import domain.models.repositories.RepositorioDePersonas;
import domain.models.repositories.RepositorioDePreguntas;
import domain.models.repositories.RepositorioGenerico;
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

    //TODO con publicaciones
    public ModelAndView mostrarPerdidas(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Mascota> mascotas = this.repo.buscarTodos();
        parametros.put("mascotas", mascotas);
        return new ModelAndView(parametros, "perdidas.hbs");
    }

    //TODO con publicaciones
    public ModelAndView mostrarEncontradas(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Mascota> mascotas = this.repo.buscarTodos();
        parametros.put("mascotas", mascotas);
        return new ModelAndView(parametros, "encontradas.hbs");
    }

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

        PersonaController cPersona = PersonaController.getInstancia();
        RepositorioDePersonas repoPersona = cPersona.getRepositorio();
        String cadena = request.queryParams("fnacPersona") + request.queryParams("nroDoc");
        String hashPersona = org.apache.commons.codec.digest.DigestUtils.md5Hex(cadena);
        Persona personaEncontrada = repoPersona.buscarPersona(hashPersona);

        if(personaEncontrada != null){
            mascota.setPersona(personaEncontrada);
        } else {
            Persona persona = new Persona();
            asignarAtributosA(persona, request);
            persona.setUsuarioTemporal(hashPersona);
            mascota.setPersona(persona);
            repoPersona.agregar(persona);
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

        PreguntasController cPreguntas = PreguntasController.getInstancia();
        RepositorioDePreguntas repoPreguntas = cPreguntas.getRepositorio();
        List<Pregunta> generales = repoPreguntas.buscarPorTipo("general");
        List<CaracteristicaConRta> elegidas = new ArrayList<>();
        for (Pregunta pregunta : generales) {
            if (request.queryParams(pregunta.getPregunta()) != null) {
                String descripcion = request.queryParams(pregunta.getPregunta());
                CaracteristicaConRta caracteristica = new CaracteristicaConRta(descripcion, "no se");
                elegidas.add(caracteristica);
            }
        }


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
            if(request.queryParams("medioPreferido").equals("EMAIL")){
                medioPreferido = Estrategia.valueOf("EMAIL");
            }
            else medioPreferido = Estrategia.valueOf("WHATSAPP");
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

        return new ModelAndView(parametros, "registro_mascota_asociacion.hbs");
    }

    public ModelAndView darEnAdopcion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "dar_adopcion.hbs");
    }
}
