package domain.models.entities.notificaciones.estrategias;

import domain.models.entities.notificaciones.Notificacion;

public interface EstrategiaDeNotificacion {
    void enviarNotificacion(Notificacion notificacion);
}
