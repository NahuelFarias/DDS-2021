package domain.notificaciones.estrategias.adapters.email;

import domain.notificaciones.Notificacion;
import java.util.Properties;


public class AdapterJavaMailEmail implements AdapterNotificadorEmail{

    public void enviarEmail(Notificacion notificacion) {
        System.out.println("Enviando email a "+ notificacion.getEmail()+" por Mailgun: '"+ notificacion.getMensaje()+"'");
    }
}
