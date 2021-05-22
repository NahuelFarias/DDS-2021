package domain.notificaciones.estrategias;

import domain.notificaciones.Contacto;

public interface EstrategiaDeNotificacion {
    void enviar(Contacto notificacion);
}
