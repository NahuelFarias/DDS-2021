package domain.models.entities.rol;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Mascota;

import domain.models.entities.mascotas.Organizacion;

import domain.models.entities.publicaciones.EstadoDePublicacion;
import domain.models.entities.publicaciones.GestorDePublicaciones;
import domain.models.entities.publicaciones.Publicacion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.publicaciones.PublicacionPerdidaNoRegistrada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rescatista implements Rol {

    private List<Mascota> mascotasRescatadas = new ArrayList<>();
    private final Integer id = 2;
    private final String nombre = "RESCATISTA";


    @Override
    public void registrarMascota(Mascota.MascotaDTO mascota, Persona persona) {

    }

    @Override
    public List<Mascota> getMascotas() {
        return mascotasRescatadas;
    }

    public void agregarMascota(Mascota.MascotaDTO mascotaDTO){
        Persona duenio = mascotaDTO.getPersona();
        Mascota mascota = new Mascota(duenio);
        mascota.inicializar(mascotaDTO);
        mascotasRescatadas.add(mascota);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override

    public void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion) {}

    @Override
    public void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion) {
    }

    @Override
    public void perdiUnaMascota(Mascota mascota) {

    }

    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto) {
        mascotaPerdida.avisarQueMeEcontraron(contacto);
        //TODO Ver lo del formulario
    }

    public void encontreUnaMascotaPerdidaSinChapita(Mascota mascotaPerdida) {
       Publicacion publicacion = new Publicacion();
       publicacion.setEstadoDePublicacion(EstadoDePublicacion.SIN_REVISAR);
       publicacion.setDescripcion(mascotaPerdida.getDescripcion());

    }

    public void encontreUnaMascotaPerdidaSinChapita(List<Foto> fotos,String descripcion,String latitud, String longitud) {
        GestorDePublicaciones gestor = new GestorDePublicaciones();
        gestor.generarPublicacionPerdidaNoRegistrada(fotos,descripcion,latitud,longitud);
        //TODO el metodo anterior deberia cambiarse a este
    }

    @Override
    public boolean tieneUsuario(Persona persona){

        return false;
    }

}

