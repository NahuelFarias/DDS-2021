package domain.models.entities.rol;

import domain.models.entities.mascotas.Mascota;
import domain.models.entities.personas.Persona;

import java.util.List;

public interface Rol {

    void registrarMascota(String nombre, String apodo, Integer edad, String descripcion, String especie, String genero, Persona persona);

    List<Mascota> getMascotas();
}
