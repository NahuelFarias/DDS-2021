package domain.models.repositories.factories;

import config.Config;
import domain.models.entities.Persistente;
import domain.models.entities.personas.Persona;
import domain.models.entities.publicaciones.GestorDePublicaciones;
import domain.models.entities.publicaciones.PublicacionEnAdopcion;
import domain.models.entities.publicaciones.PublicacionGenerica;
import domain.models.repositories.RepositorioDePersonas;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.daos.DAO;
import domain.models.repositories.daos.DAOHibernate;
import domain.models.repositories.daos.DAOMemoria;

import java.util.ArrayList;
import java.util.HashMap;

public class FactoryRepositorio {

    private static HashMap<String, RepositorioGenerico> repos;

    static {
        repos = new HashMap<>();
    }

    public static <T> RepositorioGenerico<T> get(Class<T> type){
        RepositorioGenerico<T> repo;
        if(repos.containsKey(type.getName())){
            repo = repos.get(type.getName());
        }
        else{
            if(Config.useDataBase){
                DAO<T> dao = new DAOHibernate<>(type);
                repo = new RepositorioGenerico<T>(dao);
            }
            else{
                repo = new RepositorioGenerico<T>(new DAOMemoria<>(new ArrayList<>()));
            }
            repos.put(type.toString(), repo);
        }
        return repo;
    }


}
