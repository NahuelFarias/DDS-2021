package domain.models.entities.rol;


import domain.models.entities.mascotas.Mascota;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.util.ArrayList;
import java.util.List;

public class Duenio implements Rol{
    private Integer id = 1;
    private String nombre = "DUENIO";
    //private Persona persona;
    private List<Mascota> mascotas = new ArrayList<>();


    public String getNombre() {
        return nombre;
    }

    @Override
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion,
                                 String especie, String genero, Persona persona){

        Mascota mascota = new Mascota(persona);

        mascota.inicializar(nombre, apodo, edad, descripcion, especie, genero);

        mascotas.add(mascota);
    }


}
