package domain.models.entities.rol;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.mascotas.Publicacion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.util.ArrayList;
import java.util.List;

public class Duenio implements Rol{
    private Integer id = 1;
    private String nombre = "DUENIO";
    private List<Mascota> mascotas = new ArrayList<>();


    public String getNombre() {
        return nombre;
    }

    @Override
    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto) {

    }

    @Override
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    @Override
    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion,
                                 String especie, String genero, List<CaracteristicaConRta> caracteristicas, Persona persona){

        Mascota mascota = new Mascota(persona);

        mascota.inicializar(nombre, apodo, edad, descripcion, especie, genero, caracteristicas);

        mascotas.add(mascota);
    }

    @Override
    public void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion){

    }
<<<<<<< HEAD

    @Override
    public void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion){
    }
=======
>>>>>>> 096bf3b9a848b4f4eaaea22afa2a65f7836721d3
}
