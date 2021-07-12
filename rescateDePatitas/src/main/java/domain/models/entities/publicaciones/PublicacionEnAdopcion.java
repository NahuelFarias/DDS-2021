package domain.models.entities.publicaciones;

import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;

import java.util.List;

public class PublicacionEnAdopcion extends PublicacionGenerica{
    private List<RespuestaSobrePregunta> respuestasOrganizacion;
    private List<RespuestaSobrePregunta> respuestasGenerales;//
    private Organizacion organizacion;
    private Mascota mascota;

    public void setPreguntasOrganizacion(List<RespuestaSobrePregunta> respuestasOrganizacion) {
        this.respuestasOrganizacion = respuestasOrganizacion;
    }

    public List<RespuestaSobrePregunta> getRespuestasOrganizacion() {
        return respuestasOrganizacion;
    }

    public List<RespuestaSobrePregunta> getRespuestasGenerales() {
        return respuestasGenerales;
    }

    public void setPreguntasGenerales(List<RespuestaSobrePregunta> respuestasGenerales) {
        this.respuestasGenerales = respuestasGenerales;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
