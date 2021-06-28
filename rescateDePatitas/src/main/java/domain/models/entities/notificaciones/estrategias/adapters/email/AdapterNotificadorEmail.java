package domain.models.entities.notificaciones.estrategias.adapters.email;

import domain.models.entities.notificaciones.Notificacion;

import javax.mail.MessagingException;

public interface AdapterNotificadorEmail {
    void enviarEmail(Notificacion notificacion) throws MessagingException;
}
