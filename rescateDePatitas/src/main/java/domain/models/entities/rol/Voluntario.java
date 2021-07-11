package domain.models.entities.rol;

import domain.models.entities.mascotas.*;
import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.publicaciones.EstadoDePublicacion;
import domain.models.entities.publicaciones.PreguntaAdopcion;
import domain.models.entities.publicaciones.Publicacion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.util.List;

public class Voluntario implements Rol {

    private final Integer id = 3;
    private final String nombre = "VOLUNTARIO";
    private Organizacion organizacion;

    public void setOrganizacion(Organizacion organizacion){ this.organizacion = organizacion; }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void registrarMascota(Mascota.MascotaDTO mascota, Persona persona) {

    }

    @Override
    public List<Mascota> getMascotas() {
        return null;
    }

    @Override
    public void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion) {
        unaPublicacion.setEstadoDePublicacion(EstadoDePublicacion.RECHAZADO);
    }

    @Override
    public void perdiUnaMascota(Mascota mascota){

    }

    @Override
    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto, List<Foto> fotos,
                                          String descripcion, Lugar lugar) {

    }

    public void encontreUnaMascotaPerdidaSinChapita(Mascota mascotaPerdida) {
    }

    @Override
    public void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion) {
        unaPublicacion.setEstadoDePublicacion(EstadoDePublicacion.ACEPTADO);
    }

    @Override
    public boolean tieneUsuario(Persona persona){
        return false;
    }

    public void agregarPreguntaAdopcion(PreguntaAdopcion pregunta){
        this.organizacion.agregarPreguntaAdopcion(pregunta);
    }

}
