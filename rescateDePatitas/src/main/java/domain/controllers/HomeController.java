package domain.controllers;

import domain.models.entities.mascotas.Mascota;
import domain.models.entities.personas.Usuario;
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
    private RepositorioGenerico<Usuario> repoUser;

    public HomeController(){
        this.repo = FactoryRepositorio.get(Mascota.class);
        this.repoUser = FactoryRepositorio.get(Usuario.class);
    }

    public void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(request.session().attribute("id") != null){
            Usuario usuario = repoUser.buscar(request.session().attribute("id"));
            parametros.put("usuario", usuario);
        }
    }

    public ModelAndView mostrarHome(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<Usuario> usuarios = this.repoUser.buscarTodos();
        parametros.put("usuarios", usuarios);
        asignarUsuarioSiEstaLogueado(request, parametros);
        List<Mascota> mascotas = this.repo.buscarTodos();
        parametros.put("mascotas", mascotas);
        return new ModelAndView(parametros, "index.hbs");
    }

}
