package domain.notificaciones.estrategias.adapters.wpp;

import domain.notificaciones.Contacto;

public interface AdapterNotificadorWhatsapp {
    void enviarWhatsapp(Contacto notificacion);
}
