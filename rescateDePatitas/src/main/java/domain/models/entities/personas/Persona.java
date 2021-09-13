package domain.models.entities.personas;

import domain.models.entities.Persistente;
import domain.models.entities.mascotas.*;
import domain.models.entities.publicaciones.*;
import domain.models.entities.notificaciones.estrategias.Estrategia;
import domain.models.entities.rol.Duenio;
import domain.models.entities.rol.Rescatista;
import domain.models.entities.rol.Rol;
import domain.models.entities.rol.Voluntario;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
public class Persona extends Persistente {
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fechaDeNacimiento", columnDefinition = "DATE")
    private LocalDate fechaDeNacimiento;
    @Enumerated(EnumType.STRING)
    private TipoDeDocumento tipoDoc;
    @Column(name = "numeroDocumento")
    private Integer nroDoc;
    @Column(name = "direccion")
    private String direccion;
    @OneToMany(mappedBy = "persona", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Contacto> contactos;
    @OneToMany(mappedBy = "persona", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Rol> rolesDisponibles;
    @Transient
    private Rol rolElegido;
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Persona() {
        this.contactos = new ArrayList<>();
        this.rolesDisponibles = new ArrayList<>();
    }

    public void inicializar(String nombre, String apellido, String direccion, TipoDeDocumento tipoDoc,
                            Integer nroDoc, LocalDate fechaDeNacimiento, List<Contacto> contactos) {
        setNombre(nombre);
        setApellido(apellido);
        setDireccion(direccion);
        setNroDoc(nroDoc);
        setTipoDoc(tipoDoc);
        setFechaDeNacimiento(fechaDeNacimiento);
        setContactos(contactos);
    }

    public Rol getRolElegido() {
        return rolElegido;
    }

    public List<Rol> getRolesDisponibles() {
        return rolesDisponibles;
    }

    public Rol getRol(int i) {
        return rolesDisponibles.get(i);
    }

    public void setRolElegido(Rol rolElegido) {
        this.rolElegido = rolElegido;
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

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
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
        return rolElegido;
    }

    public void setRol(Rol rol) {
        this.rolElegido = rol;
    }

    public void addRol(Rol rol) {
        this.rolesDisponibles.add(rol);
    }

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

    public List<Mascota> getMascotas() {
        if (comprobarRol("DUENIO")) {
            Duenio duenio = (Duenio) rolElegido;
            return duenio.getMascotas();
        } else if (comprobarRol("RESCATISTA")) {
            Rescatista duenio = (Rescatista) rolElegido;
            return duenio.getMascotas();
        }
        return null;
    }

    // methods

    public void agregarContacto(String nombre, String apellido, String numero, String email, Estrategia estrategiaDeEnvio) {
        Contacto contacto = new Contacto(nombre, apellido, numero, email, estrategiaDeEnvio);

        contactos.add(contacto);
    }

    public void notificarContactos(Mascota mascotaEncontrada, Contacto contactoRescatista, DatosMascotaEncontrada datos) {
        if (rolElegido.getTipo().equals("DUENIO")) {
            contactos.forEach(contacto -> contacto.notificarContacto("tu mascota " + mascotaEncontrada.getNombre() + " fue encontrada!\n" +
                    "Fue encontrada por " + contactoRescatista.getNombre() + ", sus medios de contacto son:\n" +
                    "Telefono: " + contactoRescatista.getNumeroCompleto() + "\n" + "Email: " + contactoRescatista.getEmail()));
        }

    }

    public void crearUsuario(String user, String contrasenia) {
        Usuario usuario = new Usuario(user, contrasenia);
        setUsuario(usuario);
    }

    public void registrarMascota(Mascota.MascotaDTO mascota) {
        if (rolElegido.getTipo().equals("DUENIO")) {
            Duenio duenio = (Duenio) rolElegido;
            duenio.registrarMascota(mascota, this);
        }
    }

    public void notificarContactosRescatista(Contacto contactoDuenio) {
        if (rolElegido.getTipo().equals("RESCATISTA")) {
            contactos.forEach(c -> c.notificarContacto(contactoDuenio.getNombre() + " encontro su mascota en tu publicacion!\n" +
                    "Sus medios de contacto son:\n" + "Telefono: " +
                    contactoDuenio.getNumeroCompleto() + "\n" +
                    "Email: " + contactoDuenio.getEmail()));
        }
    }


    public Boolean iniciarSesion(String user, String contrasenia) {
        return this.usuario.iniciarSesion(user, contrasenia, this);
    }

    public boolean comprobarRol(String rol) {
        return rolElegido.getTipo().equals(rol);
    }

    //Voluntario//
    public void aprobarPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion) {
        if (this.comprobarRol("VOLUNTARIO")) {
            Voluntario rolActual = (Voluntario) rolElegido;
            rolActual.aprobarPublicacion(unaPublicacion, organizacion);
        }
    }

    public void rechazarPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion) {
        if (this.comprobarRol("VOLUNTARIO")) {
            Voluntario rolActual = (Voluntario) rolElegido;
            rolActual.rechazarPublicacion(unaPublicacion, organizacion);
        }
    }

    public void enRevisionPublicacion(PublicacionGenerica unaPublicacion, Organizacion organizacion) {
        if (this.comprobarRol("VOLUNTARIO")) {
            Voluntario rolActual = (Voluntario) rolElegido;
            rolActual.enRevisionPublicacion(unaPublicacion, organizacion);
        }
    }

    //Rescatista//
    public void encontreUnaMascotaPerdida(Mascota mascotaPerdida, Contacto contactoRescatista, DatosMascotaEncontrada
            datosMascota) {
        //Con chapita
        if (this.comprobarRol("RESCATISTA")) {
            Rescatista rolActual = (Rescatista) rolElegido;
            rolActual.encontreUnaMascotaPerdida(mascotaPerdida, contactoRescatista, datosMascota);
        }
    }

    public void encontreUnaMascotaPerdidaSinChapita(Persona rescatista, DatosMascotaEncontrada datos) {
        if (this.comprobarRol("RESCATISTA")) {
            Rescatista rolActual = (Rescatista) rolElegido;
            rolActual.encontreUnaMascotaPerdidaSinChapita(this, datos);
        }
    }


    //Duenio//
    public void encontreMiMascotaPerdida(PublicacionMascotaEncontrada publicacion, Contacto contacto) {
        publicacion.getRescatista().notificarContactosRescatista(contacto);

    }

    public void perdiUnaMascota(Mascota mascota) {
        if (this.comprobarRol("DUENIO")) {
            Duenio rolActual = (Duenio) rolElegido;
            rolActual.perdiUnaMascota(mascota);
        }
    }

    public void darEnAdopcion(Mascota mascota, Organizacion organizacion, List<RespuestaConcreta> respuestasOrganizacion, List<RespuestaConcreta> respuestasGenerales1) {
        if (this.comprobarRol("DUENIO")) {
            Duenio rolActual = (Duenio) rolElegido;
            rolActual.darEnAdopcion(mascota, organizacion, respuestasOrganizacion, respuestasGenerales1);
        }

    }

    //TODO Ver como la aplicamos, es para cuando una persona no tiene un usuario
    // pero necesitamos encontrarla en la base de datos o repositorio
    public String hasheoPersona() {

        String cadena = String.valueOf(this.fechaDeNacimiento) + String.valueOf(this.nroDoc);

        String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(cadena);

        return md5;
    }


    public void notificarPosibleAdopcion(Mascota mascota, Persona adoptante) {
        contactos.forEach(contacto -> contacto.notificarContacto("alguien quiere adoptar a " + mascota.getNombre() + "!\n" +
                "Su nombre es " + adoptante.getNombre() + ", sus medios de contacto son:\n" +
                "Telefono: " + adoptante.contactos.get(0).getNumeroCompleto() + "\n" + "Email: " + adoptante.contactos.get(0).getEmail()));
    }

    public void intencionDeAdoptar(CuestionarioContestado cuestionarioContestadoPreferenciasYComodidades) {
        GestorDePublicaciones gestor = GestorDePublicaciones.getInstancia();
        gestor.generarPublicacionIntencionAdoptar(this, cuestionarioContestadoPreferenciasYComodidades);

    }

    public void notificarMascotasEnAdopcion(ArrayList hypervinculosPublicacionesEnAdopcion) {
        String hypervinculosSinFormato = "";
        for (int i = 0; i < hypervinculosPublicacionesEnAdopcion.size(); i++) {
            hypervinculosSinFormato = hypervinculosPublicacionesEnAdopcion.get(i) + "\n";
        }
        String hypervinculosConFormato = hypervinculosSinFormato;

        contactos.forEach((contacto -> contacto.notificarContacto(
                "Pensamos que estas mascotas pueden interesarte:\n" + hypervinculosConFormato)));
    }

    public List<PublicacionEnAdopcion> accederPublicacionesDeAdopcion() {
        GestorDePublicaciones gestor = GestorDePublicaciones.getInstancia();
        return gestor.getPublicacionesDeAdopcion();
    }

    public void quieroAdoptar(Mascota mascotaEnAdopcion) {
        mascotaEnAdopcion.meQuiereAdoptar(this);
    }


    public void inicializar(PersonaDTO persona) {
        this.apellido = persona.getApellido();
        this.nombre = persona.getNombre();
        this.fechaDeNacimiento = persona.getFechaDeNacimiento();
        this.tipoDoc = persona.getTipoDoc();
        this.nroDoc = persona.getNroDoc();
        this.direccion = persona.getDireccion();
        this.contactos = persona.getContactos();
        this.rolElegido = persona.getRoElegido();
        this.usuario = persona.getUsuario();
    }

    public static class PersonaDTO {
        private String nombre;
        private String apellido;
        private LocalDate fechaDeNacimiento;
        private TipoDeDocumento tipoDoc;
        private Integer nroDoc;
        private String direccion;
        private List<Contacto> contactos;
        private Rol rol;
        private Usuario usuario;

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public LocalDate getFechaDeNacimiento() {
            return fechaDeNacimiento;
        }

        public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
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

        public Rol getRoElegido() {
            return this.rol;
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

        public void inicializar(String nombre, String apellido, String direccion, TipoDeDocumento tipoDoc, Integer nroDoc,
                                LocalDate fechaDeNacimiento, List<Contacto> contactos) {
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



