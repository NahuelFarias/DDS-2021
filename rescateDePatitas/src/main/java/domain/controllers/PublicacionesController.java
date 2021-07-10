package domain.controllers;

import domain.models.entities.publicaciones.Publicacion;
import domain.models.repositories.RepositorioDePublicaciones;


public class PublicacionesController {
    private static PublicacionesController instancia;
    private RepositorioDePublicaciones repositorio;


    public static PublicacionesController getInstancia() {
        if (instancia == null) {
            instancia=new PublicacionesController();
        }
        return instancia;
    }
    private PublicacionesController(){
        this.repositorio = new RepositorioDePublicaciones();
    }


    public void crearPublicacion(Publicacion publicacion){
        this.validarDatos(); //TODO
        this.repositorio.agregar(publicacion);
    }


    private void validarDatos(){
        //TODO
    }

    public RepositorioDePublicaciones getRepositorio(){ return repositorio;}

}
