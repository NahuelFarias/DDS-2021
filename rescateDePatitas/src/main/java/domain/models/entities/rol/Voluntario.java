package domain.models.entities.rol;

import domain.models.entities.mascotas.*;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.publicaciones.EstadoDePublicacion;
import domain.models.entities.publicaciones.PreguntaAdopcion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.publicaciones.PublicacionGenerica;
import domain.models.entities.publicaciones.RespuestaSobrePregunta;

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
    public void perdiUnaMascota(Mascota mascota){

    }

    @Override
    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto,DatosMascotaPerdida datosMascota) {

    }

    @Override
    public void encontreUnaMascotaPerdidaSinChapita(Persona rescatista, DatosMascotaPerdida datosMascotaPerdida) {

    }


    @Override
    public void aprobarPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion) {
        unaPublicacion.setEstadoDePublicacion(EstadoDePublicacion.ACEPTADO);
    }

    @Override
    public void rechazarPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion) {
        unaPublicacion.setEstadoDePublicacion(EstadoDePublicacion.RECHAZADO);
    }
    @Override
    public void enRevisionPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion){
        unaPublicacion.setEstadoDePublicacion(EstadoDePublicacion.EN_REVISION);
    }

    @Override
    public boolean tieneUsuario(Persona persona){
        return false;
    }

    @Override
    public void darEnAdopcion(Mascota mascota, Organizacion organizacion, List<RespuestaSobrePregunta> respuestasOrganizacion, List<RespuestaSobrePregunta> respuestasGenerales) {

    }

    public void agregarPreguntaAdopcion(PreguntaAdopcion pregunta){
        this.organizacion.agregarPreguntaAdopcion(pregunta);
    }

}
