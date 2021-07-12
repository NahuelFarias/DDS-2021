package domain.models.repositories;

import domain.models.entities.publicaciones.PublicacionGenerica;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDePublicaciones extends RepositorioGenerico{

    private static RepositorioDePublicaciones instancia;
    public List<PublicacionGenerica> publicaciones = new ArrayList<PublicacionGenerica>();


    public static RepositorioDePublicaciones getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioDePublicaciones();
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

    public PublicacionGenerica buscar(String id){
        //TODO caracteristicas.stream().filter(d -> d.getDescripcion() == descripcion);
        PublicacionGenerica publicacion = new PublicacionGenerica();
        return publicacion;
    }
}
