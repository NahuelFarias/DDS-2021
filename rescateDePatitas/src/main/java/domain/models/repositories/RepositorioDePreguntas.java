package domain.models.repositories;

import domain.models.entities.publicaciones.Pregunta;
import domain.models.repositories.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDePreguntas extends RepositorioGenerico<Pregunta> {
    private static RepositorioDePersonas instancia;

    public static RepositorioDePersonas getInstancia(DAO dao) {
        if (instancia == null) {
            instancia=new RepositorioDePersonas(dao);
        }
        return instancia;
    }

    public RepositorioDePreguntas(DAO<Pregunta> dao) {
        super(dao);
    }

    public List<Pregunta> buscarPorTipo(String tipo) {
        return this.dao.buscarPorTipo(condicionTipo(tipo));
    }

    private BusquedaCondicional condicionTipo(String tipo) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Pregunta> preguntaQuery = criteriaBuilder.createQuery(Pregunta.class);

        Root<Pregunta> condicionRaiz = preguntaQuery.from(Pregunta.class);

        Predicate condicionTipo = criteriaBuilder.equal(condicionRaiz.get("tipoDePregunta"), tipo);

        preguntaQuery.where(condicionTipo);

        return new BusquedaCondicional(null, preguntaQuery);
    }
}
