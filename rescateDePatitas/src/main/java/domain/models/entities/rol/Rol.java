package domain.models.entities.rol;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.mascotas.Publicacion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.util.List;

public interface Rol {

    void registrarMascota(String nombre, String apodo, Integer edad, String descripcion, String especie, String genero, List<CaracteristicaConRta> caracteristicas, List<Foto> fotos, Persona persona);

    String getNombre();

    void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion);

    List<Mascota> getMascotas();

    void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion);

    void perdiUnaMascota(Mascota mascota);

    void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto);

    void tieneUsuario(Persona persona);

}
