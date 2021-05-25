package domain.notificaciones.estrategias.adapters.wpp;

import domain.notificaciones.Contacto;

public class AdapterTwilioWhatsapp implements AdapterNotificadorWhatsapp {
    public void enviarWhatsapp(Contacto contacto) {
        System.out.println("Enviando WhatsApp a "+ contacto.getNumeroCompleto()+" por Twilio: '"+ contacto.getMensaje()+"'");
    }
}
