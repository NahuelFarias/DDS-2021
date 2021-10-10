package domain.controllers;

import domain.models.entities.mascotas.Mascota;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.personas.Usuario;
import domain.models.repositories.RepositorioDeMascotas;
import domain.models.repositories.RepositorioDeUsuarios;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.factories.FactoryRepositorio;
import domain.models.repositories.factories.FactoryRepositorioMascotas;
import domain.models.repositories.factories.FactoryRepositorioUsuarios;
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

        parametros.put("tipos", tipo);
        return new ModelAndView(parametros, "registro_mascota.hbs");
    }

    public Response guardarMascota(Request request, Response response) {
        Mascota mascota = new Mascota();
        Persona persona = new Persona();
        asignarAtributosA(mascota, request);
        asignarAtributosA(persona, request);
        mascota.setPersona(persona);
        this.repo.agregar(mascota);
        //agregar persona al repo personas?
        response.redirect("/ok");

        return response;
    }

    private void asignarAtributosA(Mascota mascota, Request request) {
        if(request.queryParams("nombre") != null){
            mascota.setNombre(request.queryParams("nombre"));
        }

        if(request.queryParams("apodo") != null){
            mascota.setNombre(request.queryParams("apodo"));
        }

        if(request.queryParams("especia") != null){
            mascota.setEspecie(request.queryParams("especia"));
        }

        if(request.queryParams("sexo") != null){
            mascota.setGenero(request.queryParams("sexo"));
        }

        if(request.queryParams("tamanio") != null){
            mascota.setTamanio(request.queryParams("tamanio"));
        }

        if(request.queryParams("descripcion") != null){
            mascota.setDescripcion("descripcion");
        }

        if(request.queryParams("edad") != null){
            int edad = Integer.valueOf(request.queryParams("edad"));
            mascota.setEdad(edad);
        }
    }

    private void asignarAtributosA(Persona persona, Request request) {
    }

    public ModelAndView creada(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "ok.hbs");
    }

    public ModelAndView registroMascotaAsoc(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        return new ModelAndView(parametros, "registro_mascota_asociacion.hbs");
    }
}
