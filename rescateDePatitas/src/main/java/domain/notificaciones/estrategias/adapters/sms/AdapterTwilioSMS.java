package domain.notificaciones.estrategias.adapters.sms;

import domain.notificaciones.Contacto;

public class AdapterTwilioSMS implements AdapterNotificadorSMS {
    public void enviarSMS(Contacto notificacion) {
        System.out.println("Enviando SMS a "+ notificacion.getNumeroCompleto()+" por Twilio: '"+notificacion.getMensaje()+"'");
    }
}
