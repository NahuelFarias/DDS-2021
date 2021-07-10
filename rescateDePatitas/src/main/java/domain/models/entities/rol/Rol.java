package domain.models.entities.rol;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.publicaciones.Publicacion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.util.List;

public interface Rol {

    void registrarMascota(Mascota.MascotaDTO mascotaDTO, Persona persona);

    String getNombre();

    void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion);

    List<Mascota> getMascotas();

    void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion);

    void perdiUnaMascota(Mascota mascota);

    void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto);

    void encontreUnaMascotaPerdidaSinChapita(Mascota mascotaPerdida);

    boolean tieneUsuario(Persona persona);

}
