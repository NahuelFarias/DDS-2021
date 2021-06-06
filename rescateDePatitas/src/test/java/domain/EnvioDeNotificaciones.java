package domain;

import domain.models.entities.notificaciones.MetodoDeEnvio;
import domain.models.entities.notificaciones.Notificacion;
import domain.models.entities.notificaciones.estrategias.NotificadorEmail;
import domain.models.entities.notificaciones.estrategias.adapters.email.AdapterJavaMailEmail;
import domain.models.entities.personas.Contacto;
import domain.models.entities.notificaciones.estrategias.NotificadorSMS;
import domain.models.entities.notificaciones.estrategias.NotificadorWhatsapp;
import domain.models.entities.notificaciones.estrategias.adapters.sms.AdapterTwilioSMS;
import domain.models.entities.notificaciones.estrategias.adapters.wpp.AdapterTwilioWhatsapp;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnvioDeNotificaciones {
    Contacto contactoNotificado;

    @Before
    public void Instanciar() {
        contactoNotificado = new Contacto("Nahuel", "Farias", "+541138338092", "nfarias@frba.utn.edu.ar");
    }

    @Test
    public void EnvioNotificacionSMS() {
        AdapterTwilioSMS adapter = new AdapterTwilioSMS();
        NotificadorSMS notificadorSMS = new NotificadorSMS(adapter);
        MetodoDeEnvio metodoDeEnvio = new MetodoDeEnvio(notificadorSMS);
        Notificacion notificacion = new Notificacion(contactoNotificado, metodoDeEnvio, "Hola " + contactoNotificado.getNombre() + ", tu mascota fue encontrada!");

        adapter.enviarSMS(notificacion);
    }

    @Test
    public void EnvioNotificacionWhatsapp() {
        AdapterTwilioWhatsapp adapter = new AdapterTwilioWhatsapp();
        NotificadorWhatsapp notificadorWhatsapp = new NotificadorWhatsapp(adapter);
        MetodoDeEnvio metodoDeEnvio = new MetodoDeEnvio(notificadorWhatsapp);
        Notificacion notificacion = new Notificacion(contactoNotificado, metodoDeEnvio, "Hola " + contactoNotificado.getNombre() + ", tu mascota fue encontrada!");

        adapter.enviarWhatsapp(notificacion);
    }

    @Test
    public void EnvioNotificacionEmail() throws IOException {
        AdapterJavaMailEmail adapter = new AdapterJavaMailEmail("src/main/resources/configuration.prop", "Tu mascota fue encontrada ✨");
        NotificadorEmail notificadorEmail = new NotificadorEmail(adapter);
        MetodoDeEnvio metodoDeEnvio = new MetodoDeEnvio(notificadorEmail);
        Notificacion notificacion = new Notificacion(contactoNotificado, metodoDeEnvio, "Hola " + contactoNotificado.getNombre() + ", tu mascota fue encontrada!");

        try {
            adapter.enviarEmail(notificacion);
        } catch (InvalidParameterException | MessagingException e) {
            Logger.getLogger("Error al enviar mail").log(Level.SEVERE, null, e);
        }

    }
}
