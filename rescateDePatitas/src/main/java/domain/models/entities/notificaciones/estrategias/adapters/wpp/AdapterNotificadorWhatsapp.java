package domain.models.entities.notificaciones.estrategias.adapters.wpp;

import domain.models.entities.notificaciones.Notificacion;

public interface AdapterNotificadorWhatsapp {
    void enviarWhatsapp(Notificacion notificacion);
}
