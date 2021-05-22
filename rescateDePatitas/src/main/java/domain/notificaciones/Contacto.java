package domain.notificaciones;

public class Contacto {
    private String numeroCompleto;
    private String email;
    private String mensaje;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumeroCompleto(String numeroCompleto) {
        this.numeroCompleto = numeroCompleto;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getNumeroCompleto() {
        return numeroCompleto;
    }

    public String getEmail() {
        return email;
    }
}
