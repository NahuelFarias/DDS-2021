package domain.notificaciones.estrategias.adapters.wpp;

import domain.notificaciones.Notificacion;

public interface AdapterNotificadorWhatsapp {
    void enviarWhatsapp(Notificacion notificacion);
}
