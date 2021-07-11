package domain.models.entities.mascotas;

import domain.models.entities.personas.Persona;
import domain.models.entities.publicaciones.GestorDePublicaciones;
import domain.models.entities.publicaciones.PreguntaAdopcion;
import domain.models.entities.publicaciones.Publicacion;
import domain.models.entities.rol.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class Organizacion {
    private String nombre;
    private List<Persona> voluntarios = new ArrayList<>();
    private List<Publicacion> publicaciones = new ArrayList<>();
    private List<PreguntaAdopcion> preguntasAdopcion;
    private String latitud;
    private String longitud;

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

    public void agregarVoluntario(Persona voluntario){ this.voluntarios.add(voluntario);}

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<PreguntaAdopcion> getPreguntasAdopcion() {
        return preguntasAdopcion;
    }

    public void setPreguntasAdopcion(List<PreguntaAdopcion> preguntasAdopcion) {
        this.preguntasAdopcion = preguntasAdopcion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void generarVoluntario(Persona persona){
        Voluntario voluntario = new Voluntario();
        voluntario.setOrganizacion(this);
        persona.setRol(voluntario);
        voluntarios.add(persona);
    }

    public void controlarPublicaciones(Persona persona){
        this.getPublicaciones().forEach(publicacion -> persona.aprobarPublicacion(publicacion,this));
    }

    public void agregarPreguntaAdopcion(PreguntaAdopcion pregunta){
        this.preguntasAdopcion.add(pregunta);
    }

}