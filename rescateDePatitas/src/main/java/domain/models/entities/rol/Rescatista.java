package domain.models.entities.rol;

import domain.models.entities.mascotas.*;
import domain.models.entities.publicaciones.Cuestionario;
import domain.models.entities.publicaciones.GestorDePublicaciones;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.publicaciones.PublicacionGenerica;
import domain.models.entities.publicaciones.RespuestaSobrePregunta;

import java.util.ArrayList;
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

    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto, DatosMascotaPerdida datos) {
        //con chapita
        mascotaPerdida.avisarQueMeEcontraron(contacto,datos);
    }

    public void encontreUnaMascotaPerdidaSinChapita(Persona rescatista,DatosMascotaPerdida datosMascota) {
        GestorDePublicaciones gestor = GestorDePublicaciones.getInstancia();
        gestor.generarPublicacionPerdidaNoRegistrada(rescatista,datosMascota);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void aprobarPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion) {}

    @Override
    public void rechazarPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion) {
    }

    @Override
    public void enRevisionPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion) {

    }

    @Override
    public void perdiUnaMascota(Mascota mascota) {

    }

    @Override
    public boolean tieneUsuario(Persona persona){

        return false;
    }

    @Override
    public void darEnAdopcion(Mascota mascota, Organizacion organizacion, List<RespuestaSobrePregunta> respuestasDeLaOrg, List<RespuestaSobrePregunta> respuestasGenerales) {

    }

}

