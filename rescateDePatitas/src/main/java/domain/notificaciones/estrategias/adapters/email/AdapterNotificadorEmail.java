package domain.notificaciones.estrategias.adapters.email;

import domain.notificaciones.Contacto;

public interface AdapterNotificadorEmail {
    void enviarEmail(Contacto contacto);
}
