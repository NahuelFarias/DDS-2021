package domain.models.entities.mascotas;

import domain.models.entities.personas.Persona;
import domain.models.entities.rol.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class Organizacion {
    private String nombre;
    private List<Persona> voluntarios = new ArrayList<>();
    private List<Publicacion> publicacionesAceptadas = new ArrayList<>();
    private List<Publicacion> publicacionesARevisar = new ArrayList<>();


//  getters & setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Persona> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(List<Persona> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public List<Publicacion> getPublicacionesAceptadas() {
        return publicacionesAceptadas;
    }

    public void setPublicacionesAceptadas(List<Publicacion> publicacionesAceptadas) {
        this.publicacionesAceptadas = publicacionesAceptadas;
    }

    public List<Publicacion> getPublicacionesARevisar() {
        return publicacionesARevisar;
    }

    public void setPublicacionesARevisar(List<Publicacion> publicacionesARevisar) {
        this.publicacionesARevisar = publicacionesARevisar;
    }



    public void generarVoluntario(Persona persona){
        Voluntario voluntario = new Voluntario();
        persona.setRol(voluntario);
        this.getVoluntarios();
        voluntarios.add(persona);
    }

    public void controlarPublicaciones(Persona persona){
        this.getPublicacionesARevisar().forEach(publicacion -> persona.aprobarPublicacion(publicacion,this));
    }

}