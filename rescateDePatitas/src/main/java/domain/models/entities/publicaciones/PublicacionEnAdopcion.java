package domain.models.entities.publicaciones;

import domain.models.entities.mascotas.Mascota;

import java.util.List;

public class PublicacionEnAdopcion extends PublicacionGenerica{
    List<PreguntaAdopcion> preguntasOrganizacion;
    List<PreguntaAdopcion> preguntasGenerales;
    Mascota mascota;

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

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
