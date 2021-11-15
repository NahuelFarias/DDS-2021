package domain.controllers;

import domain.models.entities.personas.Usuario;
import domain.models.entities.rol.Rol;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.factories.FactoryRepositorio;
import spark.Request;

import java.util.Map;

public class RolController {
    private RepositorioGenerico<Rol> repo;
    private static RolController instancia;

    private RolController(){
        this.repo = FactoryRepositorio.get(Rol.class);
    }

    public static RolController getInstancia() {
        if (instancia == null) {
            instancia = new RolController();
        }
        return instancia;
    }


    public void asignarRolSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(request.session().attribute("id") != null){
            String rol = request.session().attribute("rol");

            parametros.put("rol", rol);
        }
    }

}
