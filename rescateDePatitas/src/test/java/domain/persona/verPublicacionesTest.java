package domain.persona;

import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.publicaciones.*;
import domain.models.entities.rol.Duenio;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;

public class verPublicacionesTest {
    Persona persona, adoptante;
    Contacto contacto;
    List<Contacto> contactos;
    GestorDePublicaciones gestor;
    List<RespuestaSobrePregunta> respuestasOrganizacion;
    List<RespuestaSobrePregunta> respuestasGenerales;
    Organizacion organizacion;
    Mascota.MascotaDTO mascotaDTO;
    List<CaracteristicaConRta> caracteristicasconRtas;
    List<Foto> fotos;
    Duenio duenio;
    Mascota mascotaDadaEnAdopcion,mascotaEnPublicacion;
    List<PublicacionEnAdopcion> publicacionesDeAdopcion;

    @Before
    public void setpUp() {
        persona = new Persona();
        duenio = new Duenio();
        mascotaDTO = new Mascota.MascotaDTO();
        caracteristicasconRtas = new ArrayList<>();
        fotos = new ArrayList<>();
        persona.setRol(duenio);
        contacto = new Contacto("Soledad", "Grilleta", "+541157530658", "sole.012@gmail.com", Estrategia.EMAIL);
        contactos = new ArrayList<>();
        contactos.add(contacto);
        persona.inicializar("Sole", "Grilletta", "Peru 1212,CABA", TipoDeDocumento.DNI, 3333333, 27081996,contactos);

        mascotaDTO.inicializar(persona, "Susana", "Susi", 2, "tiene una mancha blanca en una pata.",
                "gato", "hembra", caracteristicasconRtas, fotos);
        persona.registrarMascota(mascotaDTO);
        mascotaDadaEnAdopcion = persona.getRol().getMascotas().get(0);
        organizacion = new Organizacion();
        respuestasOrganizacion = new ArrayList<>();
        respuestasGenerales = new ArrayList<>();

        persona.getRol().darEnAdopcion(mascotaDadaEnAdopcion, organizacion, respuestasOrganizacion, respuestasGenerales);

        adoptante = new Persona();
        contacto = new Contacto("Fernando", "Casas", "+541111111111", "fernandocasas1234@gmail.com", Estrategia.EMAIL);
        contactos = new ArrayList<>();
        contactos.add(contacto);
        adoptante.inicializar("Fernando", "Casas", "Peru 1212,CABA", TipoDeDocumento.DNI, 3333333, 27081996,contactos);

    }

    @Test
    public void adoptarDesdePublicacion() {

        publicacionesDeAdopcion = adoptante.accederPublicacionesDeAdopcion();
        mascotaEnPublicacion =  publicacionesDeAdopcion.get(0).getMascota();
        adoptante.quieroAdoptar(mascotaEnPublicacion);

        assertSame(mascotaDadaEnAdopcion,mascotaEnPublicacion);
    }
}
