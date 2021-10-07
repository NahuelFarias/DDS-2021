package domain.controllers;

import domain.models.entities.mascotas.Mascota;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeController {
    private RepositorioGenerico<Mascota> repo;

    public HomeController(){
        this.repo = FactoryRepositorio.get(Mascota.class);
    }

    public ModelAndView mostrarHome(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Mascota> mascotas = this.repo.buscarTodos();
        parametros.put("mascotas", mascotas);
        return new ModelAndView(parametros, "index.hbs");
    }

}
