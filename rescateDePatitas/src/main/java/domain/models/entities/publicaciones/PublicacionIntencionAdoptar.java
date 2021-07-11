package domain.models.entities.publicaciones;

import domain.models.entities.personas.Persona;

import java.util.List;

public class PublicacionIntencionAdoptar extends PublicacionGenerica{
    List<PreguntaAdopcion> preferenciasYcomodidades;
    Persona adoptante;

    public List<PreguntaAdopcion> getPreferenciasYcomodidades() {
        return preferenciasYcomodidades;
    }

    public void setPreferenciasYcomodidades(List<PreguntaAdopcion> preferenciasYcomodidades) {
        this.preferenciasYcomodidades = preferenciasYcomodidades;
    }

    public Persona getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Persona adoptante) {
        this.adoptante = adoptante;
    }
}
