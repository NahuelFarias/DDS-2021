package domain.models.entities.rol;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.mascotas.Publicacion;
import domain.models.entities.personas.Persona;

import java.util.List;

public interface Rol {

    void registrarMascota(String nombre, String apodo, Integer edad, String descripcion, String especie, String genero,List<CaracteristicaConRta> caracteristicas, Persona persona);


    void aprobarPublicacion(Publicacion unaPublicacion);

    List<Mascota> getMascotas();

    void rechazarPublicacion(Publicacion unaPublicacion);


}
