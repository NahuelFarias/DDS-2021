package domain.models.entities.publicaciones;

import com.sun.org.apache.xpath.internal.operations.Or;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Organizacion;

import java.util.Date;
import java.util.List;

public class PublicacionPerdidaNoRegistrada extends PublicacionGenerica{
    private String tipoPublicacion;
    private Date fecha;
    private String descripcion;
    private EstadoDePublicacion estadoDePublicacion = EstadoDePublicacion.SIN_REVISAR;
    private List<Foto> fotos;
    private String latitud;
    private String longitud;
    private Organizacion organizacion;

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setOrganizacion(Organizacion organizacion){
        this.organizacion = organizacion;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

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

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
}
