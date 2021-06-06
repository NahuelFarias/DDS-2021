package domain.models.entities.rol;


import domain.models.entities.mascotas.Mascota;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.util.List;

public class Duenio extends Rol{
    private final Integer id = 1;
    private final String nombre = "DUENIO";
    private Persona persona;
    private List<Mascota> mascotas;


    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion,
                                 String especie, String genero){

        Mascota mascota = new Mascota(this.getPersona());

        mascota.inicializar(nombre, apodo, edad, descripcion, especie, genero);
        this.mascotas.add(mascota);
    }


}
