package domain.controllers;

import domain.models.entities.publicaciones.*;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PublicacionesPerdidasController {
    private RepositorioGenerico<PublicacionPerdidaRegistrada> repo;


    public PublicacionesPerdidasController(){
        this.repo = FactoryRepositorio.get(PublicacionPerdidaRegistrada.class);
//        this.repoEnAdopcion = FactoryRepositorio.get(PublicacionEnAdopcion.class);
//        this.repoEncontradas = FactoryRepositorio.get(PublicacionMascotaEncontrada.class);
//        this.repoIntencion = FactoryRepositorio.get(PublicacionIntencionAdopcion.class);
//
    }

//    public void crearPublicacion(PublicacionGenerica publicacion) {
//        this.validarDatos();
//        this.repo.agregar(publicacion);
//    }

    private void validarDatos() {
        //TODO
    }

//    public RepositorioGenerico<PublicacionPerdidaRegistrada> getRepo() {
//        return repo;
//    }

    public ModelAndView mostrarPerdidas(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionPerdidaRegistrada> perdidas = new ArrayList<>();

        List<PublicacionPerdidaRegistrada> publicaciones = this.repo.buscarTodos();
        for (PublicacionGenerica publi:publicaciones) {
            if(publi.getTipoPublicacion().equals("Mascota perdida registrada")){
                perdidas.add((PublicacionPerdidaRegistrada) publi);
            }
        }
        parametros.put("perdidas", publicaciones);
        return new ModelAndView(parametros, "perdidas.hbs");
    }

}
