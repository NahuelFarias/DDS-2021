package domain.notificaciones.estrategias.adapters.sms;

import domain.notificaciones.Contacto;

public class AdapterTwilioSMS implements AdapterNotificadorSMS {
    public void enviarSMS(Contacto contacto) {
        System.out.println("Enviando SMS a "+ contacto.getNumeroCompleto()+" por Twilio: '"+ contacto.getMensaje()+"'");
    }
}
