package domain.models.repositories;

import domain.models.entities.personas.Usuario;
import domain.models.repositories.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDeUsuarios extends RepositorioGenerico<Usuario> {
    //public List<Usuario> usuarios;

//        public Optional<Usuario> buscar(String nombreDeUsuario) {
//
//            return this.usuarios.stream()
//                    .filter(n -> n.getNombreDeUsuario() == nombreDeUsuario)
//                    .findFirst();
//        }

    public RepositorioDeUsuarios(DAO<Usuario> dao) {
        super(dao);
    }

    public Boolean existe(String nombreDeUsuario) {
        return buscarUsuario(nombreDeUsuario) != null;
    }

    public Usuario dameLaContrasenia(String nombreDeUsuario) {
        return buscarUsuario(nombreDeUsuario);
    }

    public Usuario buscarUsuario(String nombreDeUsuario) {
        return this.dao.buscar(condicionUsuario(nombreDeUsuario));
    }

    private BusquedaCondicional condicionUsuario(String nombreDeUsuario) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("nombreDeUsuario"), nombreDeUsuario);

        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario);

        usuarioQuery.where(condicionExisteUsuario);

        return new BusquedaCondicional(null, usuarioQuery);
    }
}
