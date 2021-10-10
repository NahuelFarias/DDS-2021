package domain.models.repositories;

import domain.models.entities.personas.Persona;
import domain.models.repositories.daos.DAO;

public class RepositorioDePersonas extends RepositorioGenerico<Persona> {
    private static RepositorioDePersonas instancia;
    //public List<Persona> personas = new ArrayList<Persona>();

    public RepositorioDePersonas(DAO<Persona> dao) {
        super(dao);
    }

    public static RepositorioDePersonas getInstancia(DAO dao) {
        if (instancia == null) {
            instancia=new RepositorioDePersonas(dao);
        }
        return instancia;
    }

//    public List<Persona> getPersonas(){
//        return this.personas;
//    }


}
