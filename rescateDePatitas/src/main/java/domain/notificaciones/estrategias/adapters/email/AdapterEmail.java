package domain.notificaciones.estrategias.adapters.email;

import domain.notificaciones.Contacto;

public class AdapterEmail implements AdapterNotificadorEmail{
    public void enviarEmail(Contacto notificacion) {
        System.out.println("Enviando email a "+ notificacion.getEmail()+" por JavaMail: '"+notificacion.getMensaje()+"'");
    }
}
