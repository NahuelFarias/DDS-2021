package domain.models.entities.publicaciones;

import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.personas.Persona;

import java.util.List;

public class PublicacionIntencionAdoptar extends PublicacionGenerica{
    List<RespuestaSobrePregunta> preferenciasYcomodidades;
    Persona adoptante;
    private Organizacion organizacion;

    @Override
    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public List<RespuestaSobrePregunta> getPreferenciasYcomodidades() {
        return preferenciasYcomodidades;
    }

    public void setPreferenciasYcomodidades(List<RespuestaSobrePregunta> preferenciasYcomodidades) {
        this.preferenciasYcomodidades = preferenciasYcomodidades;
    }

    public Persona getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Persona adoptante) {
        this.adoptante = adoptante;
    }

}
