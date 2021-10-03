package domain.models.repositories;


import domain.models.entities.personas.Usuario;
import domain.models.repositories.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioDeUsuarios extends RepositorioGenerico<Usuario> {
    public List<Usuario> usuarios;


    public Optional<Usuario> buscar(String nombreDeUsuario) {
        return this.usuarios.stream()
                .filter(n -> n.getNombreDeUsuario() == nombreDeUsuario)
                .findFirst();
    }


    public RepositorioDeUsuarios(DAO<Usuario> dao) {
        super(dao);
    }

    public Boolean existe(String nombreDeUsuario, String contrasenia) {
        return buscarUsuario(nombreDeUsuario, contrasenia) != null;
    }

    public Usuario buscarUsuario(String nombreDeUsuario, String contrasenia) {
        return this.dao.buscar(condicionUsuarioYContrasenia(nombreDeUsuario, contrasenia));
    }

    private BusquedaCondicional condicionUsuarioYContrasenia(String nombreDeUsuario, String contrasenia) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("nombreDeUsuario"), nombreDeUsuario);
        Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("contrasenia"), contrasenia);

        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

        usuarioQuery.where(condicionExisteUsuario);

        return new BusquedaCondicional(null, usuarioQuery);
    }
}
