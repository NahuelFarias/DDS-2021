package domain.models.entities.publicaciones;

import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Lugar;
import domain.models.entities.mascotas.Organizacion;

import java.util.Date;
import java.util.List;

public class PublicacionPerdidaNoRegistrada extends PublicacionGenerica{
    private String tipoPublicacion;
    private Date fecha;
    private String descripcion;
    private EstadoDePublicacion estadoDePublicacion = EstadoDePublicacion.SIN_REVISAR;
    private List<Foto> fotos;
    private Lugar lugar;
    private Organizacion organizacion;

    public void setOrganizacion(Organizacion organizacion){
        this.organizacion = organizacion;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
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

    public Lugar getLugar() {
        return lugar;
    }
}
