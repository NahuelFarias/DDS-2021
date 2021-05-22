package domain.notificaciones;

import domain.notificaciones.estrategias.EstrategiaDeNotificacion;

public class MetodoDeEnvio {
    private EstrategiaDeNotificacion estrategia;

    public void setEstrategia(EstrategiaDeNotificacion estrategia) {
        this.estrategia = estrategia;
    }

    public void enviar(Contacto notificacion) {
        this.estrategia.enviar(notificacion);
    }
}
