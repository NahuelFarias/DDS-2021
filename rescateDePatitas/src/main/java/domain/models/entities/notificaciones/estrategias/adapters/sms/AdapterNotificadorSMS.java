package domain.models.entities.notificaciones.estrategias.adapters.sms;

import domain.models.entities.notificaciones.Notificacion;

public interface AdapterNotificadorSMS {
    void enviarSMS(Notificacion notificacion);
}
