package domain.notificaciones.estrategias;

import domain.notificaciones.Notificacion;

public interface EstrategiaDeNotificacion {
    void enviarNotificacion(Notificacion notificacion);
}
