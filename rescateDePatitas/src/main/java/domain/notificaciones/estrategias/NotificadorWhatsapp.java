package domain.notificaciones.estrategias;

import domain.notificaciones.Contacto;
import domain.notificaciones.estrategias.adapters.wpp.AdapterNotificadorWhatsapp;

public class NotificadorWhatsapp implements EstrategiaDeNotificacion{
    private AdapterNotificadorWhatsapp adapter;

    public NotificadorWhatsapp(AdapterNotificadorWhatsapp adapter) {
        this.adapter = adapter;
    }

    public void setAdapter(AdapterNotificadorWhatsapp adapter) {
        this.adapter = adapter;
    }

    public void enviarNotificacion(Contacto contacto) {
        this.adapter.enviarWhatsapp(contacto);
    }
}
