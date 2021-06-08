package services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class Configuracion {


    public String leerPropiedad(String propiedad){
        String valor = null;


        try {

            Properties propiedades = new Properties();

            propiedades.load(new FileInputStream("src/main/resources/config.properties"));

             valor = propiedades.getProperty(propiedad);


        } catch (FileNotFoundException e) {
            System.out.println("Error: El archivo no existe.");
        } catch (IOException e) {
            System.out.println("Error: No se puede leer el archivo.");
        }
        return valor;
    }

    public void leerArchivoCompleto(){

        try {

            Properties propiedades = new Properties();

            propiedades.load(new FileInputStream("src/main/resources/config.properties"));

            Enumeration<Object> claves = propiedades.keys();

            while (claves.hasMoreElements()) {
                Object clave = claves.nextElement();
                System.out.println(clave.toString() + " = " + propiedades.get(clave).toString());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: El archivo no existe.");
        } catch (IOException e) {
            System.out.println("Error: No se puede leer el archivo.");
        }

    }

}
