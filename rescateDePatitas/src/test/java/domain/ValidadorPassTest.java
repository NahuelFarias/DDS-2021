package domain;

import domain.validacion.validadores.ValidadorCaracteres;
import domain.validacion.validadores.ValidadorLongitud;
import org.junit.Assert;
import org.junit.Test;

public class ValidadorPassTest {

    @Test
    public void laContrasenaEsCorrecta(){

        String password = "Hola@4124";
        String password2 = "Ho124";
        ValidadorCaracteres validadorCaracteres = new ValidadorCaracteres();

        Assert.assertTrue(validadorCaracteres.validar(password));
        Assert.assertFalse(validadorCaracteres.validar(password2));
    }

    @Test
    public void laContraseniaTieneLaCantidadCorrecta(){

        String password = "Hola_4124";
        String password2 = "Ho124";

        ValidadorLongitud validadorLongitud = new ValidadorLongitud();

        Assert.assertTrue(validadorLongitud.validar(password));
        Assert.assertFalse(validadorLongitud.validar(password2));
    }
}