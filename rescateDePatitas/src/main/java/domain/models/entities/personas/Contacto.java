package domain.models.entities.personas;

import domain.models.entities.notificaciones.MetodoDeEnvio;

public class Contacto {
    private String nombre;
    private String apellido;
    private String numeroCompleto;
    private String email;
    private MetodoDeEnvio metodoDeEnvio;
    private String mensaje;

    public Contacto(String nombre, String apellido, String numeroCompleto, String email, MetodoDeEnvio metodoDeEnvio) {
        setNombre(nombre);
        setApellido(apellido);
        setNumeroCompleto(numeroCompleto);
        setEmail(email);
        setMetodoDeEnvio(metodoDeEnvio);
    }

    public void notificarContacto(String mensaje) {
        setMensaje(mensaje);
        enviarNotificacion();
    }

    public void enviarNotificacion() {
        metodoDeEnvio.enviarNotificacion(this);
    }

    public void setMensaje(String mensaje) {
        this.mensaje = "Hola " + nombre + ", " + mensaje;
    }

    public void setMetodoDeEnvio(MetodoDeEnvio metodoDeEnvio) {
        this.metodoDeEnvio = metodoDeEnvio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumeroCompleto(String numeroCompleto) {
        this.numeroCompleto = numeroCompleto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumeroCompleto() {
        return numeroCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getMensaje() {
        return mensaje;
    }
}
