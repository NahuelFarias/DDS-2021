package domain.notificaciones.estrategias.adapters.email;

import domain.notificaciones.Notificacion;

public interface AdapterNotificadorEmail {
    void enviarEmail(Notificacion notificacion);
}
