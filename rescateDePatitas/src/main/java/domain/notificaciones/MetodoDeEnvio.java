package domain.notificaciones;

import domain.notificaciones.estrategias.EstrategiaDeNotificacion;

public class MetodoDeEnvio {
    private EstrategiaDeNotificacion estrategia;

    public MetodoDeEnvio(EstrategiaDeNotificacion estrategia) {
        setEstrategia(estrategia);
    }

    public void setEstrategia(EstrategiaDeNotificacion estrategia) {
        this.estrategia = estrategia;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        this.estrategia.enviarNotificacion(notificacion);
    }
}
