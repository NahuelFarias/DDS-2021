package domain.models.entities.mascotas;

import domain.models.entities.personas.Persona;
import domain.models.entities.publicaciones.Publicacion;
import domain.models.entities.rol.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class Organizacion {
    private String nombre;
    private List<Persona> voluntarios = new ArrayList<>();
    private List<Publicacion> publicaciones = new ArrayList<>();


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

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public void generarVoluntario(Persona persona){
        Voluntario voluntario = new Voluntario();
        persona.setRol(voluntario);
        voluntarios.add(persona);
    }

    public void controlarPublicaciones(Persona persona){
        this.getPublicaciones().forEach(publicacion -> persona.aprobarPublicacion(publicacion,this));

    }

}