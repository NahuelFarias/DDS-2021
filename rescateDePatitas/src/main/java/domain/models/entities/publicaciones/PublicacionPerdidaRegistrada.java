package domain.models.entities.publicaciones;

import domain.models.entities.mascotas.Mascota;

public class PublicacionPerdidaRegistrada extends PublicacionGenerica {
    Mascota mascota;

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
