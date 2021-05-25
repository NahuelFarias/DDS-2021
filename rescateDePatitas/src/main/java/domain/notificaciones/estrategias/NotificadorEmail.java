package domain.notificaciones.estrategias;

import domain.notificaciones.Contacto;
import domain.notificaciones.estrategias.adapters.email.AdapterNotificadorEmail;

public class NotificadorEmail implements EstrategiaDeNotificacion{
    private AdapterNotificadorEmail adapter;

    public NotificadorEmail(AdapterNotificadorEmail adapter) {
        this.adapter = adapter;
    }

    public void setAdapter(AdapterNotificadorEmail adapter) {
        this.adapter = adapter;
    }

    public void enviarNotificacion(Contacto contacto) {
        this.adapter.enviarEmail(contacto);
    }
}
