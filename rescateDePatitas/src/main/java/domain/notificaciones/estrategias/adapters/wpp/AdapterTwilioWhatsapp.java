package domain.notificaciones.estrategias.adapters.wpp;

import domain.notificaciones.Contacto;

public class AdapterTwilioWhatsapp implements AdapterNotificadorWhatsapp {
    public void enviarWhatsapp(Contacto notificacion) {
        System.out.println("Enviando WhatsApp a "+ notificacion.getNumeroCompleto()+" por Twilio: '"+notificacion.getMensaje()+"'");
    }
}
