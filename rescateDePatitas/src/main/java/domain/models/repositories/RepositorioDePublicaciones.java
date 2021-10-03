package domain.models.repositories;

import domain.models.entities.publicaciones.PublicacionGenerica;
import domain.models.repositories.daos.DAO;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDePublicaciones extends RepositorioGenerico{

    private static RepositorioDePublicaciones instancia;
    public List<PublicacionGenerica> publicaciones = new ArrayList<PublicacionGenerica>();

    protected RepositorioDePublicaciones(DAO dao) {
        super(dao);
    }

    public static RepositorioDePublicaciones getInstancia(DAO dao) {
        if (instancia == null) {
            instancia = new RepositorioDePublicaciones(dao);
        }
        return instancia;
    }

    public void agregar(PublicacionGenerica publicacion){
        this.publicaciones.add(publicacion);
    }

    public List<PublicacionGenerica> buscarTodos(){
        return publicaciones;

    }

    public void eliminar(PublicacionGenerica publicacion){
        //TODO
    }

    /*public PublicacionGenerica buscar(String id){
        //TODO caracteristicas.stream().filter(d -> d.getDescripcion() == descripcion);
        PublicacionGenerica publicacion = new PublicacionGenerica();
        return publicacion;
    }*/
}
