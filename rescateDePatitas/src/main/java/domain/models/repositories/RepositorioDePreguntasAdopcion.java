package domain.models.repositories;

import domain.models.entities.publicaciones.PreguntaAdopcion;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDePreguntasAdopcion {

    private static RepositorioDePreguntasAdopcion instancia;
    public List<PreguntaAdopcion> preguntas = new ArrayList<PreguntaAdopcion>();


    public static RepositorioDePreguntasAdopcion getInstancia() {
        if (instancia == null) {
            instancia=new RepositorioDePreguntasAdopcion();
        }
        return instancia;
    }

    public void agregar(PreguntaAdopcion pregunta){
        this.preguntas.add(pregunta);
    }

    public List<PreguntaAdopcion> buscarTodos(){
        return preguntas;

    }

    public void eliminar(PreguntaAdopcion pregunta){
        //TODO
    }

    public PreguntaAdopcion buscar(String descripcion){
        //TODO caracteristicas.stream().filter(d -> d.getDescripcion() == descripcion);
        PreguntaAdopcion pregunta = new PreguntaAdopcion();
        return pregunta;
    }
}
