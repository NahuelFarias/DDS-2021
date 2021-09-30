package domain.models.repositories;

import domain.models.repositories.daos.DAO;

import java.util.ArrayList;
import java.util.List;


public class RepositorioGenerico <T> {
    protected DAO<T> dao;
    private List<T> coleccionDeElementos;

    public RepositorioGenerico(DAO<T> dao) {
        this.dao = dao;
        this.coleccionDeElementos = new ArrayList<>();
    }

    public List<T> buscarTodos() {
        return this.coleccionDeElementos;
    }

    /*public Optional<T> buscar(Integer id){
        //SELECT
        return this.coleccionDeElementos
                .stream()
                .filter(e -> e.getId().equals(id))
                        .findFirst();
    }*/

    public void agregar(T elemento){
        //INSERT
        this.coleccionDeElementos.add(elemento);
    }

    public void modificar(T elemento){
        //UPDATE
        //TODO
    }

    public void eliminar(T elemento){
        //DELETE
        this.coleccionDeElementos.remove(elemento);
    }


}
