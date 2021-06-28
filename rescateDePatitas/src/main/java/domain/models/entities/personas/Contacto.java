package domain.models.entities.personas;

public class Contacto {
    private String nombre;
    private String apellido;
    private String numeroCompleto;
    private String email;

    public Contacto(String nombre, String apellido, String numeroCompleto, String email) {
        setNombre(nombre);
        setApellido(apellido);
        setNumeroCompleto(numeroCompleto);
        setEmail(email);
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
}
