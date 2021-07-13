package domain.models.entities.publicaciones;

import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.personas.Persona;

import java.util.Date;

public class PublicacionGenerica {
    private Date fecha;
    private String descripcion;
    private EstadoDePublicacion estadoDePublicacion = EstadoDePublicacion.SIN_REVISAR;
    private String tipoPublicacion;

    public EstadoDePublicacion getEstadoDePublicacion() {
        return estadoDePublicacion;
    }

    public void setEstadoDePublicacion(EstadoDePublicacion estadoDePublicacion) {
        this.estadoDePublicacion = estadoDePublicacion;
    }

    public Organizacion getOrganizacion(){return new Organizacion();}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(String tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public Mascota getMascota(){return new Mascota(new Persona());};

    public Persona getAdoptante() {
        return null;
    }
}
