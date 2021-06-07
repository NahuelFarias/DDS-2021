package domain.models.entities.rol;

import domain.models.entities.mascotas.Mascota;
import domain.models.entities.personas.Persona;

import java.util.List;

public class Voluntario implements Rol{

    private final Integer id = 3;
    private final String nombre = "VOLUNTARIO";

    @Override
    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion, String especie, String genero, Persona persona) {

    }

    @Override
    public List<Mascota> getMascotas() {
        return null;
    }

}
