package domain.models.entities.personas;

import domain.models.entities.validacion.validadores.ValidadorCaracteres;
import domain.models.entities.validacion.validadores.ValidadorLongitud;
import org.mindrot.jbcrypt.BCrypt;

public class Usuario {
    private static int workload = 12;
    private String nombreDeUsuario;
    private String hashContrasenia;
    private Integer fallosAlIniciarSesion = 0;

    public Usuario(String user, String contrasenia) {
        setNombreDeUsuario(user);
        if(verificarContrasenia(contrasenia)) {
            hashPassword(contrasenia);
        }
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public String getHashContrasenia() {
        return hashContrasenia;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public void setHashContrasenia(String hashContrasenia) {
        this.hashContrasenia = hashContrasenia;
    }

    public Boolean verificarContrasenia(String contrasenia){
        ValidadorCaracteres validadorCaracteres = new ValidadorCaracteres();
        ValidadorLongitud validadorLongitud = new ValidadorLongitud();

        return validadorLongitud.validar(contrasenia) && validadorCaracteres.validar(contrasenia);
    }

    public void hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        setHashContrasenia(hashed_password);
    }

    public Boolean iniciarSesion(String user, String contrasenia, Persona persona) {

        if(getNombreDeUsuario() != user) {
            System.out.println("El usuario ingresado es incorrecto");
            this.fallosAlIniciarSesion = fallosAlIniciarSesion + 1;
        } else if(!checkPassword(contrasenia, this.hashContrasenia)) {
            System.out.println("La contraseña ingresada es incorrecta");
            this.fallosAlIniciarSesion = fallosAlIniciarSesion+ 1;
        } else {
            System.out.println("Inicio de sesion exitoso!");
        }

        if(fallosAlIniciarSesion >= 3){
            persona.getContactos().forEach(contacto -> contacto.notificarContacto("detectamos una serie de ingresos fallidos en tu cuenta"));
        }

        return this.getNombreDeUsuario() == user && checkPassword(contrasenia, this.hashContrasenia);
    }

    public static Boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
    }
}
