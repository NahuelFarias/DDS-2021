package domain.services;

import org.junit.Test;
import services.Configuracion;


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
