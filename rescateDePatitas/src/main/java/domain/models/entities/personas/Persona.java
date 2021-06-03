package domain.models.entities.personas;

import domain.models.entities.Persistente;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.rol.Rol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Persona extends Persistente {
    private String nombre;
    private String apellido;
    private Integer fechaDeNacimiento;
    private TipoDeDocumento tipoDoc;
    private Integer nroDoc;
    private String direccion;
    private List<Contacto> contactos;
    private Set<Rol> roles;
    private Usuario usuario;
    private List<Mascota> mascotas;

    public Persona() {
        this.contactos = new ArrayList<Contacto>();
        this.roles = new HashSet<Rol>();
    }

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

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public void agregarRol(Rol rol){
        roles.add(rol);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void agregarContacto(String nombre, String Apellido, String numero,String email){
        //Contacto contacto = new Contacto();
        //ver si hay cambios en la clase Contacto

       // contactos.add(contacto);

    }

    public Boolean checkRol(String rol){
        return this.roles.stream().anyMatch(r->r.getNombre()==rol); //alguna clase elemento de la lista tiene de nombre el rol que indico
    }

    public void registrarMascota(String nombre,String apodo, Integer edad, String descripcion,
                                 String especie, String genero){
        //checkRol("DUENIO");

        Mascota mascota = new Mascota(this);

        mascota.inicializar(nombre, apodo, edad, descripcion, especie, genero);
        this.mascotas.add(mascota);
    }
}
