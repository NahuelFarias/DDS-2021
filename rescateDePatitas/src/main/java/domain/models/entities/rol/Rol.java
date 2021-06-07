package domain.models.entities.rol;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.personas.Persona;

import java.util.List;

public abstract class Rol {
    private Integer id;
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract void registrarMascota(String nombre, String apodo, Integer edad, String descripcion, String especie, String genero, List<CaracteristicaConRta> caracteristicas, Persona persona);

    public List<Mascota> getMascotas() {
        return null;
    }
}
