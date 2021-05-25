package domain.notificaciones.estrategias;

import domain.notificaciones.Contacto;
import domain.notificaciones.estrategias.EstrategiaDeNotificacion;
import domain.notificaciones.estrategias.adapters.sms.AdapterNotificadorSMS;

public class NotificadorSMS implements EstrategiaDeNotificacion {
    private AdapterNotificadorSMS adapter;

    public NotificadorSMS(AdapterNotificadorSMS adapter) {
        this.adapter = adapter;
    }

    public void setAdapter(AdapterNotificadorSMS adapter) {
        this.adapter = adapter;
    }

    public void enviarNotificacion(Contacto contacto) {
        this.adapter.enviarSMS(contacto);
    }
}
