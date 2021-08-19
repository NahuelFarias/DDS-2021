package domain.models.entities.mascotas;

import domain.models.entities.personas.Persona;
import domain.models.entities.publicaciones.Pregunta;
import domain.models.entities.publicaciones.PublicacionGenerica;
import domain.models.entities.rol.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class Organizacion {
    private String nombre;
    private List<Persona> voluntarios = new ArrayList<>();
    private List<PublicacionGenerica> publicaciones = new ArrayList<>();
    private List<Pregunta> preguntas;
    private Lugar ubicacion;

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

    public void agregarVoluntario(Persona voluntario) {
        this.voluntarios.add(voluntario);
    }

    public List<PublicacionGenerica> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<PublicacionGenerica> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Pregunta> getPreguntasAdopcion() {
        return preguntas;
    }

    public void setPreguntasAdopcion(List<Pregunta> preguntasAdopcion) {
        this.preguntas = preguntasAdopcion;
    }

    public Lugar getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Lugar ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void generarVoluntario(Persona persona) {
        Voluntario voluntario = new Voluntario();
        voluntario.setOrganizacion(this);
        persona.setRol(voluntario);
        voluntarios.add(persona);
    }

    public void controlarPublicaciones(Persona persona) {
        this.getPublicaciones().forEach(publicacion -> persona.aprobarPublicacion(publicacion, this));
    }

    public void agregarPreguntaAdopcion(Pregunta pregunta) {
        this.preguntas.add(pregunta);
    }

}