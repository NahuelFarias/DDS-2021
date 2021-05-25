package domain.notificaciones;

public class Contacto {
    private String nombre;
    private String apellido;
    private String numeroCompleto;
    private String email;
    private String mensaje;

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

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
