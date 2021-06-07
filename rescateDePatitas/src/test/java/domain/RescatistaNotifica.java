package domain;

import domain.models.entities.mascotas.Mascota;
import domain.models.entities.notificaciones.MetodoDeEnvio;
import domain.models.entities.notificaciones.estrategias.NotificadorEmail;
import domain.models.entities.notificaciones.estrategias.NotificadorSMS;
import domain.models.entities.notificaciones.estrategias.adapters.email.AdapterJavaMailEmail;
import domain.models.entities.notificaciones.estrategias.adapters.sms.AdapterTwilioSMS;
import domain.models.entities.personas.Persona;
import domain.models.entities.personas.TipoDeDocumento;
import domain.models.entities.rol.Duenio;
import domain.models.entities.rol.Rescatista;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class RescatistaNotifica {
    Persona personaDuenio;
    Persona personaRescatista;
    Duenio duenio;
    Rescatista rescatista;


    AdapterTwilioSMS adapterSMS;
    NotificadorSMS notificadorSMS;
    MetodoDeEnvio metodoDeEnvioSMS;
    AdapterJavaMailEmail adapterEmail;
    NotificadorEmail notificadorEmail;
    MetodoDeEnvio metodoDeEnvioEmail;


    @Before
    public void instanciar() throws IOException {
        personaDuenio = new Persona();

        personaDuenio.inicializar("Maria Victoria", "Sanchez", "Peru 1212,CABA", TipoDeDocumento.DNI, 3333333, 27081996);

        duenio = new Duenio();

        personaDuenio.setRol(duenio);

        adapterSMS = new AdapterTwilioSMS();
        notificadorSMS = new NotificadorSMS(adapterSMS);
        metodoDeEnvioSMS = new MetodoDeEnvio(notificadorSMS);
        adapterEmail = new AdapterJavaMailEmail("src/main/resources/configuration.prop", "Tu mascota fue encontrada ✨");
        notificadorEmail = new NotificadorEmail(adapterEmail);
        metodoDeEnvioEmail = new MetodoDeEnvio(notificadorEmail);

        personaDuenio.agregarContacto("Soledad", "Grilleta", "+541122222242", "sole.012@gmail.com", metodoDeEnvioEmail);

        personaDuenio.agregarContacto("Nahuel", "Farias", "+541138338092", "nfarias@frba.utn.edu.ar", metodoDeEnvioSMS);

        personaDuenio.registrarMascota("Susana", "Susi", 2, "tiene una mancha blanca en una pata.",
                "gato", "hembra", personaDuenio);

        personaRescatista = new Persona();

        personaRescatista.inicializar("Roberto Francisco", "Ginez", "Brasil 1112,CABA", TipoDeDocumento.DNI, 33311111, 30031998);

        personaRescatista.agregarContacto("Roberto Francisco", "Ginez", "+541138138227", "rginez@gmail.com", metodoDeEnvioSMS);

        rescatista = new Rescatista();

        personaRescatista.setRol(rescatista);
    }

    @Test
    public void elRescatistaEncuentraUnaMascotaConQR() {
        Mascota mascotaPerdida = personaDuenio.getRol().getMascotas().get(0);

        personaRescatista.getRol().encontreMascota(mascotaPerdida, personaRescatista.getContactos().get(0));
    }
}