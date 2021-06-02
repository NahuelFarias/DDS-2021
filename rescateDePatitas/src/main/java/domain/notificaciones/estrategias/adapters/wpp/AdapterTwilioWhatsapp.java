package domain.notificaciones.estrategias.adapters.wpp;

import domain.notificaciones.Notificacion;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class AdapterTwilioWhatsapp implements AdapterNotificadorWhatsapp {
    public static final String ACCOUNT_SID = "ACf9a15ce926e3f281df16a70f1f7624ea";
    public static final String AUTH_TOKEN = "2fbf0173773dddeb20c92eabf81114f2";

    public void enviarWhatsapp(Notificacion notificacion) {
        System.out.println("Enviando WhatsApp a "+ notificacion.getNumeroCompleto()+" por Twilio: '"+ notificacion.getMensaje()+"'");

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+5491138338092"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                notificacion.getMensaje())
                .create();

        System.out.println(message.getSid());
    }
}
