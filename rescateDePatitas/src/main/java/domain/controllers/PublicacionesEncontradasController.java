package domain.controllers;

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

public class PublicacionesEncontradasController {
    private RepositorioGenerico<PublicacionMascotaEncontrada> repo;


    public PublicacionesEncontradasController(){
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
}
