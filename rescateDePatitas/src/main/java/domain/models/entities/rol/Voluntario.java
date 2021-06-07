package domain.models.entities.rol;

import domain.models.entities.personas.Persona;

public class Voluntario extends Rol{

    private final Integer id = 3;
    private final String nombre = "VOLUNTARIO";

    @Override
    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion, String especie, String genero, Persona persona) {

    }

}
