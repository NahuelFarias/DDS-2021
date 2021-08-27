package domain.models.entities.publicaciones;

import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;

import java.util.List;

public class PublicacionEnAdopcion extends PublicacionGenerica{
//    private List<RespuestaSobrePregunta> respuestasOrganizacion;
    private List<RespuestaSobrePregunta> respuestasGenerales;//
    private Organizacion organizacion;
    private Mascota mascota;
    private Cuestionario cuestionariodeAdopcion;

    public Cuestionario getCuestionario() {
        return cuestionariodeAdopcion;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionariodeAdopcion = cuestionario;
    }

//    public void setRespuestasOrganizacion(List<RespuestaSobrePregunta> respuestasOrganizacion) { this.respuestasOrganizacion = respuestasOrganizacion; }

//    public List<RespuestaSobrePregunta> getRespuestasOrganizacion() { return respuestasOrganizacion; }


    public List<RespuestaSobrePregunta> getRespuestasGenerales() {
        return respuestasGenerales;
    }

    public void setRespuestasGenerales(List<RespuestaSobrePregunta> respuestasGenerales) {
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
