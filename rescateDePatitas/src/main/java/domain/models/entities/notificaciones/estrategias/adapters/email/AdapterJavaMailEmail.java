package domain.models.entities.notificaciones.estrategias.adapters.email;

import domain.models.entities.notificaciones.Notificacion;


public class AdapterJavaMailEmail implements AdapterNotificadorEmail{

    public void enviarEmail(Notificacion notificacion) {
        System.out.println("Enviando email a "+ notificacion.getEmail()+" por Mailgun: '"+ notificacion.getMensaje()+"'");
    }
}
