package domain.models.entities.rol;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Mascota;

import domain.models.entities.mascotas.Organizacion;

import domain.models.entities.mascotas.CaracteristicaConRta;

import domain.models.entities.mascotas.Publicacion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.util.List;

public class Rescatista implements Rol {

    private final Integer id = 2;
    private final String nombre = "RESCATISTA";

    @Override
    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion, String especie,
                                 String genero, List<CaracteristicaConRta> caracteristicas, List<Foto> fotos, Persona persona) {

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

    public void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion) {}

    @Override
    public void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion) {
    }

    @Override
    public void perdiUnaMascota(Mascota mascota) {

    }

    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto) {
        mascotaPerdida.avisarQueMeEcontraron(contacto);
    }
}

