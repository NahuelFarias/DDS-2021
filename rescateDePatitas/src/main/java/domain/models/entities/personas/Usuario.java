package domain.models.entities.personas;

import domain.models.entities.Persistente;
import domain.models.entities.validacion.validadores.ValidadorCaracteres;
import domain.models.entities.validacion.validadores.ValidadorLongitud;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario extends Persistente {
    @Transient
    private static int workload = 12;
    @Column(name = "nombreDeUsuario")
    private String nombreDeUsuario;
    @Column(name = "contrasenia")
    private String contrasenia;
    @Transient
    private Integer fallosAlIniciarSesion = 0;

    public Usuario(){

    }

    public Usuario(String user, String contrasenia) {
        setNombreDeUsuario(user);
        if(verificarContrasenia(contrasenia)) {
            hashPassword(contrasenia);
        }
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public void setContrasenia(String hashContrasenia) {
        this.contrasenia = hashContrasenia;
    }

    public Boolean verificarContrasenia(String contrasenia){
        ValidadorCaracteres validadorCaracteres = new ValidadorCaracteres();
        ValidadorLongitud validadorLongitud = new ValidadorLongitud();

        return validadorLongitud.validar(contrasenia) && validadorCaracteres.validar(contrasenia);
    }

    public void hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        setContrasenia(hashed_password);
    }

    public Boolean iniciarSesion(String user, String contrasenia, Persona persona) {

        if(getNombreDeUsuario() != user) {
            System.out.println("El usuario ingresado es incorrecto");
            this.fallosAlIniciarSesion = fallosAlIniciarSesion + 1;
        } else if(!checkPassword(contrasenia, this.contrasenia)) {
            System.out.println("La contraseña ingresada es incorrecta");
            this.fallosAlIniciarSesion = fallosAlIniciarSesion+ 1;
        } else {
            System.out.println("Inicio de sesion exitoso!");
            this.fallosAlIniciarSesion = 0;
        }

        if(fallosAlIniciarSesion >= 3){
            persona.getContactos().forEach(contacto -> contacto.notificarContacto("detectamos una serie de ingresos fallidos en tu cuenta"));
        }

        return this.getNombreDeUsuario() == user && checkPassword(contrasenia, this.contrasenia);
    }

    public Boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
    }

}
