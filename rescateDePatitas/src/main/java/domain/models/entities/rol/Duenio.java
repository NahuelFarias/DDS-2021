package domain.models.entities.rol;

import com.google.zxing.WriterException;
import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.publicaciones.Publicacion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.io.IOException;
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
    public void perdiUnaMascota(Mascota mascota) {
        mascota.avisarQueMePerdi();
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
                                 String especie, String genero, List<CaracteristicaConRta> caracteristicas,
                                 List<Foto> fotos, Persona persona){

        Mascota mascota = new Mascota(persona);

        try {
            mascota.generarQR();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }

        mascota.inicializar(nombre, apodo, edad, descripcion, especie, genero, caracteristicas,fotos);

        mascotas.add(mascota);
    }

    @Override
    public void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion){ }
    @Override
    public void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion){
    }
}
