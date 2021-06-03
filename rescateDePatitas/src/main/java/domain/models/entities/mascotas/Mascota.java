package domain.models.entities.mascotas;

import com.google.zxing.WriterException;
import domain.models.entities.GeneradorQR;
import domain.models.entities.Persistente;
import domain.models.entities.personas.Persona;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mascota extends Persistente {
    private String nombre;
    private String apodo;
    private String idMascota; //tiene que ser unico
    private String descripcion;
    private Integer edad;
    private String especie;
    private String genero;
    private Organizacion organizacion; //quitar?
    private List<Caracteristica> caracteristicas;
    private List<Foto> fotos;
    private Persona persona;

    public Mascota(Persona persona) {
        this.caracteristicas = new ArrayList<Caracteristica>();
        this.fotos = new ArrayList<Foto>();
        this.persona = persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getIdMascota() { return idMascota; }

    public void setIdMascota(String idMascota) { this.idMascota = idMascota; }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public void agregarFoto(Foto foto){ fotos.add(foto); }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void generarQR() throws IOException, WriterException {
        String urlMascota = "www.rescatedepatitas.com.ar/" + idMascota;
        String path = "C:\\QR\\" + idMascota + ".jpg";

        GeneradorQR generador = new GeneradorQR();
        generador.generarQR(urlMascota,path,"jpg",500,500);
    }

    public void inicializar(String nombre,String apodo, Integer edad, String descripcion,
                            String especie, String genero){
        //this.setIdMascota(idMascota);
        this.setApodo(apodo);
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setDescripcion(descripcion);
        this.setEspecie(especie);
        this.setGenero(genero);



    }






}
