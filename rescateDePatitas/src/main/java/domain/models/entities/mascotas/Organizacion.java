package domain.models.entities.mascotas;

import domain.models.entities.personas.Persona;
import domain.models.entities.rol.Voluntario;

import java.util.List;

public class Organizacion {
    private String nombre;
    //private List<Administrador> administradores;
    private List<Persona> voluntarios;
    public static List<Publicacion> publicacionesAceptadas;
    public static List<Publicacion> publicacionesARevisar;


//  getters & setters


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public void generarVoluntario(Persona persona) {
        Voluntario voluntario = new Voluntario();
        persona.setRol(voluntario);
        this.voluntarios.add(persona);
    }

    public void controlarPublicaciones(Voluntario voluntario){
        this.getPublicacionesARevisar().forEach(publicacion -> voluntario.aprobarPublicacion(publicacion));
    }

}