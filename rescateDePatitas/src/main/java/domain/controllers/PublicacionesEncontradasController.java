package domain.controllers;

import domain.models.entities.hogares.Hogar;
import domain.models.entities.hogares.ListadoDeHogares;
import domain.models.entities.mascotas.DatosMascotaEncontrada;
import domain.models.entities.mascotas.Lugar;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.publicaciones.PublicacionMascotaEncontrada;
import domain.models.entities.rol.Rescatista;
import domain.models.repositories.RepositorioDePersonas;
import domain.models.entities.publicaciones.EstadoDePublicacion;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.factories.FactoryRepositorio;
import org.junit.Assert;
import services.BuscadorDeHogaresDeTransito;
import services.FiltradorDeHogaresDeTransito;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.*;

public class PublicacionesEncontradasController {
    private RepositorioGenerico<PublicacionMascotaEncontrada> repo;
    private UsuarioController usuarioController = new UsuarioController();
    private RolController rolController = new RolController();

    public PublicacionesEncontradasController() {
        this.repo = FactoryRepositorio.get(PublicacionMascotaEncontrada.class);
    }

    public ModelAndView mostrarEncontradas(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionMascotaEncontrada> encontradas = this.repo.buscarTodos();

        //filtrar por si esta aprobada
//        for (PublicacionGenerica publi:publicaciones) {
//            if(publi.getTipoPublicacion().equals("Mascota perdida no registrada")){
//                encontradas.add((PublicacionMascotaEncontrada) publi);
//            }
//        }
        parametros.put("encontradas", encontradas);
        return new ModelAndView(parametros, "encontradas.hbs");
    }

    public ModelAndView mostrarHogares(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        //parametros.put("hogares",)

        return new ModelAndView(parametros, "registro_encontrada_asociacion.hbs");
    }

    public ModelAndView encontrada(Request request, Response response) {
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

        UsuarioController usuarioController = UsuarioController.getInstancia();
        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);

        parametros.put("tipos", tipo);
        parametros.put("provincias", provincias);

        return new ModelAndView(parametros, "registro_encontrada.hbs");
    }

    public Response guardarEncontrada(Request request, Response response) {
        PublicacionMascotaEncontrada publi = new PublicacionMascotaEncontrada();

        asignarAtributosA(publi, request);

        if (request.session().attribute("id") != null) {
            //Si esta logueado lo busco en el repo y lo agrego como rescatista
            RepositorioDePersonas repoPersonas = RepositorioDePersonas.getInstancia();
            Persona rescatista = repoPersonas.dameLaPersona(request.session().attribute("id"));
            publi.setRescatista(rescatista);
        } else {
            // Si NO esta logueado lo busco por HASH
            PersonaController cPersona = PersonaController.getInstancia();
            RepositorioDePersonas repoPersona = cPersona.getRepositorio();
            String cadena = request.queryParams("fnacPersona") + request.queryParams("nroDoc");
            String hashPersona = org.apache.commons.codec.digest.DigestUtils.md5Hex(cadena);
            Persona personaEncontrada = repoPersona.buscarPersona(hashPersona);

            if (personaEncontrada != null) {
                //Si encontré a la persona la seteo como rescatista
                publi.setRescatista(personaEncontrada);
            } else {
                // Si no la encontré la agrego y la seteo como rescatista
                Persona persona = new Persona();
                asignarAtributosA(persona, request);
                persona.setUsuarioTemporal(hashPersona);
                publi.setRescatista(persona);
                repoPersona.agregar(persona);
            }
        }

        String especie = "";
        if (request.queryParams("especie") != null) {
            especie = request.queryParams("especie");
            //request.session().attribute("especie", especie);
        }

        String tamanio = "";
        if (request.queryParams("tamanio") != null) {
            tamanio = request.queryParams("tamanio");
            //request.session().attribute("tamanio", tamanio);
        }

        BuscadorDeHogaresDeTransito buscadorDeHogaresDeTransito = BuscadorDeHogaresDeTransito.getInstancia();
        ListadoDeHogares listadoDeHogares = null;
        try {
            listadoDeHogares = new ListadoDeHogares();
            listadoDeHogares = buscadorDeHogaresDeTransito.listadoDeHogares(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Hogar> hogares = listadoDeHogares.hogares;

        FiltradorDeHogaresDeTransito filtrador = new FiltradorDeHogaresDeTransito();
        //TODO considerar null de hogares
        //hogares = filtrador.filtrarPorCapacidad(hogares);
        //hogares = filtrador.filtrarPorAnimalAceptado(hogares, especie.toLowerCase());
        //hogares = filtrador.filtrarPorTamanio(hogares, tamanio);
        //hogares = filtrador.filtrarPorCercania(hogares, Integer.parseInt(request.queryParams("radio")),
        //        Double.parseDouble(request.queryParams("latitud")), Double.parseDouble(request.queryParams("longitud")));

        //TODO falta considerar caracteristicas
        //hogares = filtrador.filtrarPorCaracteristica(hogares, String caracteristicas)

        request.session().attribute("hogares", hogares);

        this.repo.agregar(publi);

        response.redirect("/encontrada_hogares");
        return response;
    }

    private void asignarAtributosA(PublicacionMascotaEncontrada publi, Request request) {
        DatosMascotaEncontrada datosMascota = new DatosMascotaEncontrada();
        double latitud = 0;
        double longitud = 0;

        if (request.queryParams("descripcion") != null) {
            datosMascota.setDescripcion(request.queryParams("descripcion"));
        }

        if (request.queryParams("latitud") != null) {
            latitud = Double.parseDouble(request.queryParams("latitud"));
            request.session().attribute("latitud", latitud);
        }

        if (request.queryParams("longitud") != null) {
            longitud = Double.parseDouble(request.queryParams("longitud"));
            request.session().attribute("longitud", longitud);
        }

        Lugar lugar = new Lugar();
        lugar.setLatitud(latitud);
        lugar.setLongitud(longitud);

        publi.setTipoPublicacion("Mascota encontrada");

        datosMascota.setLugar(lugar);
        publi.setDatosMascotaEncontrada(datosMascota);

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

        Rescatista rolRescatista = new Rescatista();
        persona.addRol(rolRescatista);

    }


    public ModelAndView revisar_encontrada(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);

        rolController.asignarRolSiEstaLogueado(request, parametros);

        List<PublicacionMascotaEncontrada> encontradas = this.repo.buscarTodos();
        parametros.put("encontradas", encontradas);
        return new ModelAndView(parametros, "revisar_encontrada.hbs");
    }

    public ModelAndView revisar_publi(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);

        rolController.asignarRolSiEstaLogueado(request, parametros);

        PublicacionMascotaEncontrada publicacion = this.repo.buscar(new Integer(request.params("id")));
        parametros.put("publicacion", publicacion);
        return new ModelAndView(parametros, "revisar_encontrada_publi.hbs");
    }

    public Response aprobar(Request request, Response response) {
        PublicacionMascotaEncontrada publicacion = this.repo.buscar(new Integer(request.params("id")));
        publicacion.setEstadoDePublicacion(EstadoDePublicacion.ACEPTADO);
        this.repo.modificar(publicacion);

        response.redirect("/revisar_encontrada");

        return response;
    }

    public Response rechazar(Request request, Response response) {
        PublicacionMascotaEncontrada publicacion = this.repo.buscar(new Integer(request.params("id")));
        publicacion.setEstadoDePublicacion(EstadoDePublicacion.RECHAZADO);
        this.repo.modificar(publicacion);

        response.redirect("/revisar_encontrada");

        return response;
    }

    public ModelAndView publicacionEnviada(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);

        rolController.asignarRolSiEstaLogueado(request, parametros);

        return new ModelAndView(parametros, "publicacion_enviada.hbs");
    }
}
