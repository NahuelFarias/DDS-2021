package domain.models.entities.rol;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.mascotas.Publicacion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.util.List;

public class Voluntario implements Rol {

    private final Integer id = 3;
    private final String nombre = "VOLUNTARIO";

    public String getNombre() {
        return nombre;
    }

    @Override
    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion, String especie, String genero, List<CaracteristicaConRta> caracteristicas, Persona persona) {

    }

    @Override
    public List<Mascota> getMascotas() {
        return null;
    }

    @Override
    public void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion) {
        organizacion.getPublicacionesARevisar().remove(unaPublicacion);
    }

    @Override
    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto) {

    }

    @Override
    public void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion) {
        organizacion.getPublicacionesAceptadas().add(unaPublicacion);
        organizacion.getPublicacionesARevisar().remove(unaPublicacion);

    }
}
