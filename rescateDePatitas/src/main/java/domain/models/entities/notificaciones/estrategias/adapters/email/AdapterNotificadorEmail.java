package domain.models.entities.notificaciones.estrategias.adapters.email;

import domain.models.entities.notificaciones.Notificacion;

public interface AdapterNotificadorEmail {
    void enviarEmail(Notificacion notificacion);
}
