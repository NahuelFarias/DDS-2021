package domain.models.entities.personas;

import domain.models.entities.validacion.validadores.ValidadorCaracteres;
import domain.models.entities.validacion.validadores.ValidadorLongitud;

public class Usuario {
    private String nombreDeUsuario;
    private String hashContrasenia;

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getHashContrasenia() {
        return hashContrasenia;
    }

    public void setHashContrasenia(String hashContrasenia) {
        this.hashContrasenia = hashContrasenia;
    }

    public Boolean verificarContrasenia(String contrasenia){
        ValidadorCaracteres validadorCaracteres = new ValidadorCaracteres();
        ValidadorLongitud validadorLongitud = new ValidadorLongitud();

        return validadorLongitud.validar(contrasenia) && validadorCaracteres.validar(contrasenia);
    }
}
