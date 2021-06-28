package domain.models.entities.notificaciones.estrategias;

import domain.models.entities.notificaciones.Notificacion;
import domain.models.entities.notificaciones.estrategias.adapters.sms.AdapterNotificadorSMS;

public class NotificadorSMS implements EstrategiaDeNotificacion {
    private AdapterNotificadorSMS adapter;

    public NotificadorSMS(AdapterNotificadorSMS adapter) {
        setAdapter(adapter);
    }

    public void setAdapter(AdapterNotificadorSMS adapter) {
        this.adapter = adapter;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        this.adapter.enviarSMS(notificacion);
    }
}