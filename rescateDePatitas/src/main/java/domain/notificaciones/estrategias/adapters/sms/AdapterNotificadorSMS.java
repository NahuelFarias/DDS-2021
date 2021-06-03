package domain.notificaciones.estrategias.adapters.sms;

import domain.notificaciones.Notificacion;

public interface AdapterNotificadorSMS {
    void enviarSMS(Notificacion notificacion);
}
