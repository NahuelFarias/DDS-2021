package domain.models.entities.rol;

import domain.models.entities.mascotas.Mascota;
<<<<<<< HEAD
import domain.models.entities.mascotas.Organizacion;
=======
import domain.models.entities.mascotas.CaracteristicaConRta;
>>>>>>> 096bf3b9a848b4f4eaaea22afa2a65f7836721d3
import domain.models.entities.mascotas.Publicacion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.util.List;

public class Rescatista implements Rol {

    private final Integer id = 2;
    private final String nombre = "RESCATISTA";

    @Override
    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion, String especie, String genero, List<CaracteristicaConRta> caracteristicas, Persona persona) {

    }

    @Override
    public List<Mascota> getMascotas() {
        return null;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
<<<<<<< HEAD
    public void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion){}
    @Override
    public void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion){
=======
    public void aprobarPublicacion(Publicacion unaPublicacion){}

    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto) {
        mascotaPerdida.avisarQueMeEcontraron(contacto);
>>>>>>> 096bf3b9a848b4f4eaaea22afa2a65f7836721d3
    }
}
