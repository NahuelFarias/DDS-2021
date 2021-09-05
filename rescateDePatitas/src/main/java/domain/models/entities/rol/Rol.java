package domain.models.entities.rol;

import domain.models.entities.mascotas.*;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.publicaciones.Cuestionario;
import domain.models.entities.publicaciones.PublicacionGenerica;
import domain.models.entities.publicaciones.RespuestaSobrePregunta;

import java.util.List;

public interface Rol {

    void registrarMascota(Mascota.MascotaDTO mascotaDTO, Persona persona);

    String getNombre();

    void aprobarPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion);

    List<Mascota> getMascotas();

    void rechazarPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion);

    void enRevisionPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion);

    void perdiUnaMascota(Mascota mascota);

    void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto,DatosMascotaPerdida datos);

    void encontreUnaMascotaPerdidaSinChapita(Persona rescatista,DatosMascotaPerdida datosMascotaPerdida);

    boolean tieneUsuario(Persona persona);

    void darEnAdopcion(Mascota mascota, Organizacion organizacion, List<RespuestaSobrePregunta> respuestasDeLaOrg,
                       List<RespuestaSobrePregunta> respuestasGenerales);

}
