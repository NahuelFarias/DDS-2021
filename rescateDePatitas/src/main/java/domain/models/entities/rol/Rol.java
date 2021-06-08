package domain.models.entities.rol;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.mascotas.Publicacion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.util.List;

public interface Rol {

    void registrarMascota(String nombre, String apodo, Integer edad, String descripcion, String especie, String genero,List<CaracteristicaConRta> caracteristicas, Persona persona);

<<<<<<< HEAD

    void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion);

    List<Mascota> getMascotas();

    void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion);


=======
    void aprobarPublicacion(Publicacion unaPublicacion);

    List<Mascota> getMascotas();

    String getNombre();

    void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto);
>>>>>>> 096bf3b9a848b4f4eaaea22afa2a65f7836721d3
}
