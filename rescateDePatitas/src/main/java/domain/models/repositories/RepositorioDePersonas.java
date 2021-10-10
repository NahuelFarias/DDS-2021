package domain.models.repositories;

import domain.models.entities.personas.Persona;
import domain.models.entities.personas.Usuario;
import domain.models.repositories.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioDePersonas extends RepositorioGenerico<Persona> {
    private static RepositorioDePersonas instancia;
    //public List<Persona> personas = new ArrayList<Persona>();

    public RepositorioDePersonas(DAO<Persona> dao) {
        super(dao);
    }

    //es necesario esto?
    public static RepositorioDePersonas getInstancia(DAO dao) {
        if (instancia == null) {
            instancia=new RepositorioDePersonas(dao);
        }
        return instancia;
    }

    public Persona dameLaPersona(int usuario_id) {
        return buscarPersona(usuario_id);
    }

    public Persona buscarPersona(int id) {
        return this.dao.buscar(condicionPersona(id));
    }

    private BusquedaCondicional condicionPersona(int usuario_id) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Persona> usuarioQuery = criteriaBuilder.createQuery(Persona.class);

        Root<Persona> condicionRaiz = usuarioQuery.from(Persona.class);

        Predicate condicionId = criteriaBuilder.equal(condicionRaiz.get("usuario"), usuario_id);

        Predicate condicionExistePersona = criteriaBuilder.and(condicionId);

        usuarioQuery.where(condicionExistePersona);

        return new BusquedaCondicional(null, usuarioQuery);
    }
}
