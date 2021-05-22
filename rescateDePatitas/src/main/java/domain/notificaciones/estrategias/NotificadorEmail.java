package domain.notificaciones.estrategias;

import domain.notificaciones.Contacto;
import domain.notificaciones.estrategias.adapters.email.AdapterNotificadorEmail;

public class NotificadorEmail {
    private AdapterNotificadorEmail adapter;

    public NotificadorEmail(AdapterNotificadorEmail adapter) {
        this.adapter = adapter;
    }

    public void setAdapter(AdapterNotificadorEmail adapter) {
        this.adapter = adapter;
    }

    public void enviar(Contacto notificacion) {
        this.adapter.enviarEmail(notificacion);
    }
}
