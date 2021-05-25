package domain.notificaciones.estrategias.adapters.sms;

import domain.notificaciones.Contacto;

public interface AdapterNotificadorSMS {
    void enviarSMS(Contacto contacto);
}
