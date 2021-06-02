package domain.notificaciones.estrategias.adapters.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import domain.notificaciones.Notificacion;

public class AdapterTwilioSMS implements AdapterNotificadorSMS {
    public static final String ACCOUNT_SID = "ACf9a15ce926e3f281df16a70f1f7624ea";
    public static final String AUTH_TOKEN = "2fbf0173773dddeb20c92eabf81114f2";

    public void enviarSMS(Notificacion notificacion) {
        System.out.println("Enviando SMS a "+ notificacion.getNumeroCompleto()+" por Twilio: '"+ notificacion.getMensaje()+"'");

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(notificacion.getNumeroCompleto()),
                new PhoneNumber("+12242231661"),
                notificacion.getMensaje()).create();

        System.out.println(message.getSid());
    }
}
