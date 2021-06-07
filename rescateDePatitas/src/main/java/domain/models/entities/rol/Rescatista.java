package domain.models.entities.rol;

import domain.models.entities.mascotas.Mascota;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

public class Rescatista extends Rol {

    private final Integer id = 2;
    private final String nombre = "RESCATISTA";

    @Override
    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion,
                                 String especie, String genero, Persona persona) {
    }

    public void encontreMascota(Mascota unaMascota, Contacto contacto) {
        unaMascota.avisarQueMeEcontraron(contacto);
    }
}
