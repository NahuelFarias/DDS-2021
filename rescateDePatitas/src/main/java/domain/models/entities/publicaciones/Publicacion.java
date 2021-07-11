package domain.models.entities.publicaciones;

import java.util.Date;

public class Publicacion {
    private Date fecha;

    private String descripcion;

    private EstadoDePublicacion estadoDePublicacion = EstadoDePublicacion.SIN_REVISAR;

    public EstadoDePublicacion getEstadoDePublicacion() {
        return estadoDePublicacion;
    }

    public void setEstadoDePublicacion(EstadoDePublicacion estadoDePublicacion) {
        this.estadoDePublicacion = estadoDePublicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
