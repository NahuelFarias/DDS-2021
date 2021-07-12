package domain.models.entities.rol;

import com.google.zxing.WriterException;
import domain.models.entities.mascotas.*;
import domain.models.entities.publicaciones.*;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import domain.models.repositories.RepositorioDeUsuarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Duenio implements Rol{
    private Integer id = 1;
    private String nombre = "DUENIO";
    private List<Mascota> mascotas = new ArrayList<>();
    //en un controller
    private RepositorioDeUsuarios repositorioDeUsuarios;

    public String getNombre() {
        return nombre;
    }

    @Override
    public void perdiUnaMascota(Mascota mascota) {
        mascota.avisarQueMePerdi();
    }

    @Override
    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto, List<Foto> fotos,
                                          String descripcion, Lugar lugar) {
    }

    @Override
    public void encontreUnaMascotaPerdidaSinChapita(Persona rescatista, DatosMascotaPerdida datosMascotaPerdida) {

    }

    public void encontreUnaMascotaPerdidaSinChapita(Mascota mascotaPerdida) {
    }

    @Override
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    @Override
    public void registrarMascota(Mascota.MascotaDTO mascotaDTO, Persona persona){

        Mascota mascota = new Mascota(persona);

        try {
            mascota.generarQR();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }

        mascota.inicializar(mascotaDTO);

        mascotas.add(mascota);
    }

    @Override
    public boolean tieneUsuario(Persona persona){
      return this.repositorioDeUsuarios.buscar(persona.getUsuario().getNombreDeUsuario()).isPresent();
    }

    @Override
    public void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion){ }
    @Override
    public void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion){
    }

    public void darEnAdopcion(Mascota mascota, Organizacion organizacion,List<RespuestaSobrePregunta> respuestasOrganizacion,
                              List<RespuestaSobrePregunta> respuestasGenerales){
        //TODO Preguntas
        GestorDePublicaciones gestor = new GestorDePublicaciones();
        gestor.generarPublicacionEnAdopcion(mascota,respuestasOrganizacion,respuestasGenerales,organizacion);

    }


}
