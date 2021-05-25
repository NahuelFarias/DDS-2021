package domain.notificaciones.estrategias;

import domain.notificaciones.Contacto;

public interface EstrategiaDeNotificacion {
    void enviarNotificacion(Contacto contacto);
}
