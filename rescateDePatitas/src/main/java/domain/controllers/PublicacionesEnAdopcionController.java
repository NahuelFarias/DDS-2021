package domain.controllers;

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
}
