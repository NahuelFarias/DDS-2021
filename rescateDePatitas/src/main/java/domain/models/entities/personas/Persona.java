package domain.models.entities.personas;

import domain.models.entities.Persistente;
import domain.models.entities.mascotas.*;
import domain.models.entities.publicaciones.*;
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

    //en un controller
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

    public void registrarMascota(Mascota.MascotaDTO mascota){
        this.rol.registrarMascota(mascota,this);
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

    public void notificarContactosRescatista(Contacto contactoDuenio) {
        if(rol.getNombre() == "RESCATISTA"){
            contactos.forEach( c -> c.notificarContacto(contactoDuenio.getNombre() + " encontro su mascota en tu publicacion!\n" +
                    "Sus medios de contacto son:\n" + "Telefono: " +
                    contactoDuenio.getNumeroCompleto() + "\n" +
                    "Email: " + contactoDuenio.getEmail()));
        }
    }

    public void crearUsuario(String user, String contrasenia){
        Usuario usuario = new Usuario(user, contrasenia);
        setUsuario(usuario);
    }

    public Boolean iniciarSesion(String user, String contrasenia) {
        return this.usuario.iniciarSesion(user, contrasenia, this);
    }
    
    public void aprobarPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion){
        this.rol.aprobarPublicacion(unaPublicacion, organizacion);
    }

    public void rechazarPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion){
        this.rol.rechazarPublicacion(unaPublicacion, organizacion);
    }

    public void enRevisionPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion){
        this.rol.enRevisionPublicacion(unaPublicacion, organizacion);
    }

    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contactoRescatista, List<Foto> fotos,
                                          String descripcion, Lugar lugar) {
        //Con chapita
        this.rol.encontreUnaMascotaPerdida(mascotaPerdida, contactoRescatista, fotos, descripcion, lugar);
    }

    public void encontreUnaMascotaPerdidaSinChapita(Persona rescatista,DatosMascotaPerdida datos) {
        this.rol.encontreUnaMascotaPerdidaSinChapita(this,datos);
    }

    public void encontreMiMascotaPerdida(PublicacionPerdidaNoRegistrada publicacion,Contacto contacto)  {
        publicacion.getRescatista().notificarContactosRescatista(contacto);

    }

    public String hasheoPersona(){

        String cadena = String.valueOf(this.fechaDeNacimiento) + String.valueOf(this.nroDoc) ;

        String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex( cadena );

        return md5;
    }

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

    public void notificarPosibleAdopcion(Mascota mascota,Persona adoptante){
        contactos.forEach(contacto -> contacto.notificarContacto("Alguien quiere adoptar a " + mascota.getNombre() +  "!\n" +
                "Su nombre es " + adoptante.getNombre() + ", sus medios de contacto son:\n" +
                "Telefono: " + adoptante.contactos.get(0).getNumeroCompleto() + "\n" + "Email: " + adoptante.contactos.get(0).getEmail()));
    }

    public void intencionDeAdoptar(List<RespuestaSobrePregunta> respuestasGenerales,Lugar ubicacion){
        //TODO Debe estar asociado a una organizacion
        GestorDePublicaciones gestor =  GestorDePublicaciones.getInstancia();
        PublicacionIntencionAdoptar nuevaPublicacion = gestor.generarPublicacionIntencionAdoptar(this,respuestasGenerales);

        nuevaPublicacion.setOrganizacion(gestor.buscarOrganizacionMasCercana(ubicacion));
    }


    public void inicializar(PersonaDTO persona) {
        this.apellido = persona.getApellido();
        this.nombre = persona.getNombre();
        this.fechaDeNacimiento = persona.getFechaDeNacimiento();
        this.tipoDoc = persona.getTipoDoc();
        this.nroDoc = persona.getNroDoc();
        this.direccion = persona.getDireccion();
        this.contactos = persona.getContactos();
        this.rol = persona.getRol();
        this.usuario = persona.getUsuario();
    }

    public static class PersonaDTO {
        private String nombre;
        private String apellido;
        private Integer fechaDeNacimiento;
        private TipoDeDocumento tipoDoc;
        private Integer nroDoc;
        private String direccion;
        private List<Contacto> contactos;
        private Rol rol;
        private Usuario usuario;

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

        public Rol getRol() {
            return rol;
        }

        public void setRol(Rol rol) {
            this.rol = rol;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
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

        public void inicializar(String nombre,String apellido, String direccion,TipoDeDocumento tipoDoc,Integer nroDoc,
               Integer fechaDeNacimiento,List<Contacto> contactos) {
            this.apellido = apellido;
            this.nombre = nombre;
            this.fechaDeNacimiento = fechaDeNacimiento;
            this.tipoDoc = tipoDoc;
            this.nroDoc = nroDoc;
            this.direccion = direccion;
            this.contactos = contactos;
            this.rol = rol;
            this.usuario = usuario;
        }
    }

}
