package domain;

import domain.models.entities.EditorDeFotos;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.notificaciones.MetodoDeEnvio;
import domain.models.entities.notificaciones.estrategias.NotificadorEmail;
import domain.models.entities.notificaciones.estrategias.adapters.email.AdapterJavaMailEmail;
import domain.models.entities.personas.Contacto;
import domain.models.entities.notificaciones.estrategias.NotificadorSMS;
import domain.models.entities.notificaciones.estrategias.NotificadorWhatsapp;
import domain.models.entities.notificaciones.estrategias.adapters.sms.AdapterTwilioSMS;
import domain.models.entities.notificaciones.estrategias.adapters.wpp.AdapterTwilioWhatsapp;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnvioDeNotificaciones {
    Contacto contactoNotificadoSMS;
    Contacto contactoNotificadoWhatsapp;
    Contacto contactoNotificadoEmail;

    AdapterTwilioSMS adapterSMS;
    NotificadorSMS notificadorSMS;
    MetodoDeEnvio metodoDeEnvioSMS;

    AdapterTwilioWhatsapp adapterWhatsapp;
    NotificadorWhatsapp notificadorWhatsapp;
    MetodoDeEnvio metodoDeEnvioWhatsapp;

    AdapterJavaMailEmail adapterEmail;
    NotificadorEmail notificadorEmail;
    MetodoDeEnvio metodoDeEnvioEmail;

    @Before
    public void Instanciar() throws IOException {
        adapterSMS = new AdapterTwilioSMS();
        notificadorSMS = new NotificadorSMS(adapterSMS);
        metodoDeEnvioSMS = new MetodoDeEnvio(notificadorSMS);
        contactoNotificadoSMS = new Contacto("Nahuel", "Farias", "+541138338092", "nfarias@frba.utn.edu.ar", metodoDeEnvioSMS);

        adapterWhatsapp = new AdapterTwilioWhatsapp();
        notificadorWhatsapp = new NotificadorWhatsapp(adapterWhatsapp);
        metodoDeEnvioWhatsapp = new MetodoDeEnvio(notificadorWhatsapp);
        contactoNotificadoWhatsapp = new Contacto("Nahuel", "Farias", "+541138338092", "nfarias@frba.utn.edu.ar", metodoDeEnvioWhatsapp);

        adapterEmail = new AdapterJavaMailEmail("src/main/resources/configuration.prop", "Tu mascota fue encontrada ✨");
        notificadorEmail = new NotificadorEmail(adapterEmail);
        metodoDeEnvioEmail = new MetodoDeEnvio(notificadorEmail);
        contactoNotificadoEmail = new Contacto("Nahuel", "Farias", "+541138338092", "nfarias@frba.utn.edu.ar", metodoDeEnvioEmail);
    }

    @Test
    public void EnvioNotificacionSMS() {
        contactoNotificadoSMS.notificarContacto("tu mascota fue encontrada!");
    }

    @Test
    public void EnvioNotificacionWhatsapp() {
        contactoNotificadoWhatsapp.notificarContacto("tu mascota fue encontrada!");
    }

    @Test
    public void EnvioNotificacionEmail() {

        try {
            contactoNotificadoEmail.notificarContacto("tu mascota fue encontrada!");;
        } catch (InvalidParameterException e) {
            Logger.getLogger("Error al enviar mail").log(Level.SEVERE, null, e);
        }

    }
}
