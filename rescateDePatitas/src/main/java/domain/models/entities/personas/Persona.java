package domain.models.entities.personas;

import domain.models.entities.Persistente;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.CaracteristicaConRta;
import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.publicaciones.Publicacion;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.rol.Rol;

import domain.models.repositories.RepositorioDeUsuarios;

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

    //en un controler
    private RepositorioDeUsuarios repositorioDeUsuarios;

    public Persona() {
        this.contactos = new ArrayList<>();
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
                            Integer nroDoc,Integer fechaDeNacimiento,List<Contacto> contactos ){
        setNombre(nombre);
        setApellido(apellido);
        setDireccion(direccion);
        setNroDoc(nroDoc);
        setTipoDoc(tipoDoc);
        setFechaDeNacimiento(fechaDeNacimiento);
        setContactos(contactos);
    }

    public void registrarMascota(String nombre, String apodo, Integer edad, String descripcion,
                                 String especie, String genero, ArrayList<CaracteristicaConRta> caracteristicas, List<Foto> fotos, Persona persona){
        persona = this;

        this.rol.registrarMascota(nombre,apodo,edad,descripcion,especie,genero,caracteristicas,fotos,persona);

    }

    public void agregarContacto(String nombre, String apellido, String numero, String email, Estrategia estrategiaDeEnvio){
        Contacto contacto = new Contacto(nombre,apellido,numero,email, estrategiaDeEnvio);

        contactos.add(contacto);
    }

    public void notificarContactos(Mascota mascotaEncontrada, Contacto contactoRescatista) {
        if(rol.getNombre() == "DUENIO") {
           contactos.forEach(contacto -> contacto.notificarContacto("tu mascota " + mascotaEncontrada.getNombre() +  " fue encontrada!\n" +
                   "Fue encontrada por " + contactoRescatista.getNombre() + ", sus medios de contacto son:\n" +
                   "Telefono: " + contactoRescatista.getNumeroCompleto() + "\n" + "Email: " + contactoRescatista.getEmail()));
        }
    }

    public void crearUsuario(String user, String contrasenia){
        Usuario usuario = new Usuario(user, contrasenia);
        setUsuario(usuario);
    }

    public Boolean iniciarSesion(String user, String contrasenia) {
        return this.usuario.iniciarSesion(user, contrasenia, this);
    }
    
    public void aprobarPublicacion(Publicacion unaPublicacion, Organizacion organizacion){
        this.rol.aprobarPublicacion(unaPublicacion, organizacion);
    }

    public void rechazarPublicacion(Publicacion unaPublicacion, Organizacion organizacion){
        this.rol.rechazarPublicacion(unaPublicacion, organizacion);
    }

    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contacto){
        this.rol.encontreUnaMascotaPerdida(mascotaPerdida, contacto);
    }

    public String hasheoPersona(){

        String cadena = String.valueOf(this.fechaDeNacimiento) + String.valueOf(this.nroDoc) ;

        String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex( cadena );

        return md5;
    }
// en usuario ?
    public void login(String nombre, String apellido,String direccion,TipoDeDocumento tipoDoc,
                            Integer nroDoc,Integer fechaDeNacimiento,List<Contacto> contactos,String nombreDeUsuario,String pwd){

        Persona persona = new Persona();
        this.inicializar(nombre, apellido,direccion,tipoDoc,nroDoc,fechaDeNacimiento,contactos);

        if(this.rol.tieneUsuario(persona)) {
            System.out.println("Usted ya tiene un usuario");
        }
        else{
            persona.setUsuario(usuario);
            Usuario usuario = new Usuario(nombreDeUsuario,pwd);
            this.repositorioDeUsuarios.aniadir(usuario);
        }
    }

}
