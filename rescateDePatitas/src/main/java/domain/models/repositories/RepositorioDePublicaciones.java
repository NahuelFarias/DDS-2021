package domain.models.repositories;

import domain.models.entities.publicaciones.Publicacion;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDePublicaciones extends RepositorioGenerico{

    private static RepositorioDePublicaciones instancia;
    public List<Publicacion> caracteristicas = new ArrayList<Publicacion>();


    public static RepositorioDePublicaciones getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioDePublicaciones();
        }
        return instancia;
    }

    public void agregar(Publicacion publicacion){
        this.caracteristicas.add(publicacion);
    }

    public List<Publicacion> buscarTodos(){
        return caracteristicas;

    }

    public void eliminar(Publicacion publicacion){
        //TODO
    }

    public Publicacion buscar(String id){
        //TODO caracteristicas.stream().filter(d -> d.getDescripcion() == descripcion);
        Publicacion publicacion = new Publicacion();
        return publicacion;
    }
}
