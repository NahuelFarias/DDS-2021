package domain.models.entities.rol;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.personas.Persona;

import java.util.List;

public class Rescatista extends Rol {

    private final Integer id = 2;
    private final String nombre = "RESCATISTA";

    @Override
    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion, String especie, String genero, List<CaracteristicaConRta> caracteristicas, Persona persona) {

    }
}
