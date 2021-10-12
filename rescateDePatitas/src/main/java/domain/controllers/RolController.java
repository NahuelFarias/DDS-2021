package domain.controllers;

import domain.models.entities.personas.Persona;
import domain.models.entities.rol.Rol;
import domain.models.repositories.RepositorioDePersonas;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.daos.DAOHibernate;
import domain.models.repositories.factories.FactoryRepositorio;
import spark.Request;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RolController {
    private DAOHibernate daoHibernate = new DAOHibernate();
    private RepositorioGenerico<Rol> repo;
    private RepositorioDePersonas repoPersonas = new RepositorioDePersonas(daoHibernate);

    public RolController(){
        this.repo = FactoryRepositorio.get(Rol.class);
    }

    public void asignarRolSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(request.session().attribute("id") != null){
            Persona persona = this.repoPersonas.dameLaPersona(request.session().attribute("id"));
//            try {
//                for (int i = 0; i < 10; i++) {
//                    TimeUnit.SECONDS.sleep(2);
//                }
//            }catch(Exception e) {
//            }
            Rol rol = persona.getRolElegido();
            parametros.put("rol", rol);
        }
    }

}
