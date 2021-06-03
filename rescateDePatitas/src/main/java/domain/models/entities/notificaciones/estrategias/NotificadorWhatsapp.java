package domain.models.entities.notificaciones.estrategias;

import domain.models.entities.notificaciones.Notificacion;
import domain.models.entities.notificaciones.estrategias.adapters.wpp.AdapterNotificadorWhatsapp;

public class NotificadorWhatsapp implements EstrategiaDeNotificacion{
    private AdapterNotificadorWhatsapp adapter;

    public NotificadorWhatsapp(AdapterNotificadorWhatsapp adapter) {
        this.adapter = adapter;
    }

    public void setAdapter(AdapterNotificadorWhatsapp adapter) {
        this.adapter = adapter;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        this.adapter.enviarWhatsapp(notificacion);
    }
}
