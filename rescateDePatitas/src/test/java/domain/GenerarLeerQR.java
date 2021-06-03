package domain;

import domain.models.entities.GeneradorQR;
import domain.models.entities.LectorQR;
import org.junit.Test;


public class GenerarLeerQR {

    String data = "http://www.google.com";
    String path = "C:\\QR\\qr.jpg";
    String path2 = "C:\\QR\\qr_kaspersky.png";


    @Test
    public void generarQRcorrecto() throws Exception {

        GeneradorQR generador = new GeneradorQR();

        generador.generarQR(data,path,"jpg",500,500);

    }

    @Test
    public void leerQRcorrecto(){
        LectorQR lector = new LectorQR();

        lector.leerQR(path);
        lector.leerQR(path2);
    }
}
