package domain.controllers;

import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.publicaciones.PublicacionEnAdopcion;
import domain.models.entities.publicaciones.PublicacionGenerica;
import domain.models.entities.publicaciones.PublicacionMascotaEncontrada;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicacionesEnAdopcionController {
    private RepositorioGenerico<PublicacionEnAdopcion> repo;


    public PublicacionesEnAdopcionController(){
        this.repo = FactoryRepositorio.get(PublicacionEnAdopcion.class);
    }

    public ModelAndView mostrarEnAdopcion(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        List<PublicacionEnAdopcion> adopciones = this.repo.buscarTodos();

        parametros.put("adopciones", adopciones);
        return new ModelAndView(parametros, "adopcion.hbs");
    }

    public ModelAndView mostrarPublicacionEnAdopcion(Request request, Response response) {
        PublicacionEnAdopcion publicacion = this.repo.buscar(new Integer(request.params("id")));
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("publicacion", publicacion);
        return new ModelAndView(parametros, "adopcion_publicacion.hbs");
    }

    public Response quieroAdoptarlo(Request request, Response response){
        Persona persona = new Persona();

        if(request.queryParams("nombre") != null){
            crearContacto(persona, request);
        }
        if(request.queryParams("") != null){

        }
        
        response.redirect("/en_adopcion/ok");
        return response;
    }

    public ModelAndView mostrarOk(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros, "adopcion_publicacion_ok.hbs");
    }

    public void crearContacto(Persona persona, Request request){
        if(request.queryParams("nombre") != null){
            persona.setNombre(request.queryParams("nombre"));
        }
        Contacto contacto = new Contacto("","",
                request.queryParams("telefono"),
                request.queryParams("email"),
                Estrategia.EMAIL);
        persona.getContactos().add(contacto);
    }
}
