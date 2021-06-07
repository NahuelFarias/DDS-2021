package domain.models.entities.personas;

import domain.models.entities.Persistente;
import domain.models.entities.mascotas.Publicacion;
import domain.models.entities.rol.Rol;

import java.util.ArrayList;
import java.util.List;

public class Persona extends Persistente {
    private String nombre;
    private String apellido;
    private Integer fechaDeNacimiento;
    private TipoDeDocumento tipoDoc;
    private Integer nroDoc;
    private String direccion;
    private List<Contacto> contactos;
    private Rol rol;
    private Usuario usuario;

    public Persona() {
        this.contactos = new ArrayList<Contacto>();
    }


    //getters & setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public TipoDeDocumento getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(TipoDeDocumento tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public Integer getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(Integer nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol){this.rol = rol;}


    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    // methods

    public void inicializar(String nombre, String apellido, String direccion,TipoDeDocumento tipoDoc,
                            Integer nroDoc,Integer fechaDeNacimiento ){
        setNombre(nombre);
        setApellido(apellido);
        setDireccion(direccion);
        setNroDoc(nroDoc);
        setTipoDoc(tipoDoc);
        setFechaDeNacimiento(fechaDeNacimiento);

    }

    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion,
                                 String especie, String genero,Persona persona){

        this.rol.registrarMascota(nombre,apodo,edad,descripcion,especie,genero,persona);

    }

    public void agregarContacto(String nombre, String apellido, String numero,String email){
        Contacto contacto = new Contacto(nombre,apellido,numero,email);

        contactos.add(contacto);

    }

    public void crearUsuario(){
        Usuario usuario = new Usuario();
        setUsuario(usuario);
        //TODO

    }
    
    public void aprobarPublicacion(Publicacion unaPublicacion){

        this.rol.aprobarPublicacion(unaPublicacion);
    }

}
