package domain.duenio;

import domain.controllers.CaracteristicasController;
import domain.models.entities.mascotas.Caracteristica;
import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.rol.Duenio;
import domain.models.entities.rol.Rescatista;
import org.junit.Before;
import org.junit.Test;
import services.EditorDeFotos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificarPosibleAdopcionAlDuenio {

    Persona personaDuenio;
    Persona adoptante;
    Duenio duenio;
    Rescatista rescatista;

    CaracteristicasController controller;
    CaracteristicaConRta caracteristicaConRta1;
    CaracteristicaConRta caracteristicaConRta2;
    ArrayList<CaracteristicaConRta> caracteristicasConRtas;
    List<Caracteristica> caracteristicas;
    ArrayList<String> rtas, rtas2;

    Contacto contacto1,contacto2,contacto3;
    List<Contacto> contactos, contactosRescatista;
    Foto foto;
    List<Foto> fotos;
    EditorDeFotos editor;

    @Before
    public void instanciar() throws IOException {
        personaDuenio = new Persona();

        contacto1 = new Contacto("Soledad", "Grilleta", "+541157530658", "sole.012@gmail.com", Estrategia.EMAIL);
        contactos = new ArrayList<>();
        contactos.add(contacto1);
        personaDuenio.inicializar("Sole", "Grilletta", "Peru 1212,CABA", TipoDeDocumento.DNI, 3333333, 27081996,contactos);
        duenio = new Duenio();
        personaDuenio.setRol(duenio);

        caracteristicasConRtas = new ArrayList<>();
        fotos = new ArrayList<>();
        Mascota.MascotaDTO mascotaDTO = new Mascota.MascotaDTO();
        mascotaDTO.inicializar(personaDuenio,"Susana","Susi",2,"tiene una mancha blanca en una pata.",
                "gato", "hembra", caracteristicasConRtas, fotos);

        personaDuenio.registrarMascota(mascotaDTO);

        adoptante = new Persona();

        contacto2 = new Contacto("Roberto Francisco", "Ginez", "+541138138227", "rginez@gmail.com", Estrategia.SMS);

        contactosRescatista = new ArrayList<>();
        contactosRescatista.add(contacto2);

        adoptante.inicializar("Roberto Francisco", "Ginez", "Brasil 1112,CABA", TipoDeDocumento.DNI, 33311111, 30031998, contactosRescatista);

    }

    @Test
    public void notificarPosibleAdopcionAlDuenio() {
        Mascota mascota = personaDuenio.getRol().getMascotas().get(0);
        mascota.meQuiereAdoptar(adoptante);
    }
}
