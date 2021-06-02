package notificaciones;

import domain.notificaciones.Contacto;
import domain.notificaciones.MetodoDeEnvio;
import domain.notificaciones.Notificacion;
import domain.notificaciones.estrategias.NotificadorSMS;
import domain.notificaciones.estrategias.NotificadorWhatsapp;
import domain.notificaciones.estrategias.adapters.sms.AdapterTwilioSMS;
import domain.notificaciones.estrategias.adapters.wpp.AdapterTwilioWhatsapp;
import org.junit.Before;
import org.junit.Test;

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
        Notificacion notificacion = new Notificacion(contactoNotificado, metodoDeEnvio, "Hola, tu mascota fue encontrada!");

        adapter.enviarSMS(notificacion);
    }

    @Test
    public void EnvioNotificacionWhatsapp() {
        AdapterTwilioWhatsapp adapter = new AdapterTwilioWhatsapp();
        NotificadorWhatsapp notificadorWhatsapp = new NotificadorWhatsapp(adapter);
        MetodoDeEnvio metodoDeEnvio = new MetodoDeEnvio(notificadorWhatsapp);
        Notificacion notificacion = new Notificacion(contactoNotificado, metodoDeEnvio, "Hola, tu mascota fue encontrada!");

        adapter.enviarWhatsapp(notificacion);
    }
}
