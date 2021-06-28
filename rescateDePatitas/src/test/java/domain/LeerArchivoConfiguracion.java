package domain;

import org.junit.Before;
import org.junit.Test;


public class LeerArchivoConfiguracion {


    @Test
    public void leerArchivoConfiguracion() {

        Configuracion configuracion = new Configuracion();

        configuracion.leerArchivoCompleto();

    }

    @Test
    public void leerUnaPropiedad(){
        Configuracion configuracion = new Configuracion();

        String propiedad = configuracion.leerPropiedad("url");
        System.out.println(propiedad);

    }
}
