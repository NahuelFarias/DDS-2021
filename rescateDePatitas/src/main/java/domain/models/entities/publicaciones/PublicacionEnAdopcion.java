package domain.models.entities.publicaciones;

import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;

import java.util.List;

public class PublicacionEnAdopcion extends PublicacionGenerica{
    private List<PreguntaAdopcion> preguntasOrganizacion;
    private List<PreguntaAdopcion> preguntasGenerales;
    private Organizacion organizacion;
    private Mascota mascota;

    public List<PreguntaAdopcion> getPreguntasOrganizacion() {
        return preguntasOrganizacion;
    }

    public void setPreguntasOrganizacion(List<PreguntaAdopcion> preguntasOrganizacion) {
        this.preguntasOrganizacion = preguntasOrganizacion;
    }

    public List<PreguntaAdopcion> getPreguntasGenerales() {
        return preguntasGenerales;
    }

    public void setPreguntasGenerales(List<PreguntaAdopcion> preguntasGenerales) {
        this.preguntasGenerales = preguntasGenerales;
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
