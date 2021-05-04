package domain;

import domain.validacion.ValidadorCaracteres;
import domain.validacion.ValidadorLongitud;
import domain.validacion.ValidadorPassword;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgoritmoPassword {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        Boolean validez;
        Boolean noSegura = true;
        HashSet<String> setDePasswords = new HashSet<>(cargarLista());
        do {
            System.out.println("Por favor escriba la Password.");
            String password = lector.nextLine();
            if (validez = verificarValidez(password)) {
                if (noSegura = setDePasswords.contains(password)){
                    System.out.println("Password no segura");
                } else {
                    System.out.println("Password Segura");
                }
            }
        } while (!validez || noSegura);

    }
    // Esta funcion devuelve un HashSet<String> con las 10000 peores contrasenias(solo las de longitud mayor a 8)
    // Excepcion en caso de no encontrar el archivo: Imprime en pantalla un mensaje de error y devuelve un HashSet vacio
    public static HashSet<String> cargarLista() {
        try{
            HashSet<String> miSet = new HashSet<>();
            File lista = new File("10k-worst-passwords.txt");
            Scanner myReader = new Scanner(lista);
            String password;
            while (myReader.hasNextLine()) {
                password = myReader.nextLine();
                if (password.length() >= 8) {
                    miSet.add(password);
                }
            }
        return miSet;
        } catch(FileNotFoundException e){
            System.out.println("Error leyendo el archivo");
            e.printStackTrace();
        }
        return new HashSet<>();
    }
    // Esta funcion devuelve un bool de valor verdadero despues de verificar si el string recibido contiene
    // una minuscula, una mayuscula, un numero, un caracter especial y no contiene espacios.
    public static Boolean verificarValidez(String password) {
        Boolean esValida = true;
        ValidadorCaracteres validadorCaracteres = new ValidadorCaracteres();
        ValidadorLongitud validadorLongitud = new ValidadorLongitud();

        esValida = validadorLongitud.validar(password) && validadorCaracteres.validar(password);

        return esValida;
    }
}

