package domain.controllers;

import domain.models.entities.personas.Usuario;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.factories.FactoryRepositorio;
import spark.Request;

import java.util.Map;

public class UsuarioController {
    private RepositorioGenerico<Usuario> repo;

    public UsuarioController(){
        this.repo = FactoryRepositorio.get(Usuario.class);
    }

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repo.buscar(request.session().attribute("id"));
            parametros.put("usuario", usuario);
        }
    }

}
