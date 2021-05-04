package domain.validacion;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCaracteres implements ValidadorPassword {
    String mayusculas = "(.*[A-Z].*)";
    String minusculas = "(.*[a-z].*)";
    String numeros = "(.*[0-9].*)";
    String caracteresEspeciales = "(.*[@#$%^&+=].*)";
    String espacio = "(.\\S+)";


    @Override
    public boolean validar(String password) {
        if (password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=.*\\S+)$")) {
            validarPatron(mayusculas, password,"debe contener al menos una mayuscula");
            validarPatron(minusculas, password,"debe contener al menos una minuscula");
            validarPatron(numeros, password,"debe contener al menos un numero");
            validarPatron(caracteresEspeciales, password,"debe contener al menos un caracter especial");
            validarPatron(espacio, password,"no puede contener espacios en blanco");
            return false;
        }
        return true;
    }

    public void validarPatron(String patron, String password, String message) {
        if (!password.matches(patron)) {
            System.out.println("La Password " + message);
        }
    }
}
