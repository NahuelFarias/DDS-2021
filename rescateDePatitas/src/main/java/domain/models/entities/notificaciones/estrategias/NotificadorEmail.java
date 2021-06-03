package domain.models.entities.notificaciones.estrategias;

import domain.models.entities.notificaciones.Notificacion;
import domain.models.entities.notificaciones.estrategias.adapters.email.AdapterNotificadorEmail;

public class NotificadorEmail implements EstrategiaDeNotificacion{
    private AdapterNotificadorEmail adapter;

    public NotificadorEmail(AdapterNotificadorEmail adapter) {
        setAdapter(adapter);
    }

    public void setAdapter(AdapterNotificadorEmail adapter) {
        this.adapter = adapter;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        this.adapter.enviarEmail(notificacion);
    }
}
