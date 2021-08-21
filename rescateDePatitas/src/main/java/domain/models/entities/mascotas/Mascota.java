package domain.models.entities.mascotas;

import com.google.zxing.WriterException;
import domain.models.entities.publicaciones.GestorDePublicaciones;
import services.Configuracion;
import services.GeneradorQR;
import domain.models.entities.Persistente;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Mascota extends Persistente {
    private String nombre;
    private String apodo;
    private static Integer idMascota = 0;
    private String descripcion;
    private Integer edad;
    private String especie;
    private String genero;
    private Organizacion organizacion;
    private List<CaracteristicaConRta> caracteristicas;
    private List<Foto> fotos;
    private Persona persona;

    public Mascota(Persona persona) {
        this.caracteristicas = new ArrayList<>();
        this.fotos = new ArrayList<>();
        this.idMascota = getIdMascota() + 1;
        this.persona = persona;
        this.fotos = new ArrayList<>();
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

    public Integer getIdMascota() {
        return idMascota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getEspecie() {
        return especie;
    }

    public String getGenero() {
        return genero;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public List<CaracteristicaConRta> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaConRta> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public void agregarFoto(Foto foto) {
        fotos.add(foto);
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void generarQR() throws IOException, WriterException {

        Configuracion configuracion = new Configuracion();

        String url = configuracion.leerPropiedad("url") + "/" + idMascota.toString();

        String pathGuardar = configuracion.leerPropiedad("pathQR") + idMascota.toString() + ".jpg";

        GeneradorQR generador = new GeneradorQR();
        generador.generarQR(url, pathGuardar, "jpg", 500, 500);
    }

    public void avisarQueMePerdi() {
        GestorDePublicaciones gestor = GestorDePublicaciones.getInstancia();
        gestor.generarPublicacionPerdidaRegistrada(this);
    }

    public void avisarQueMeEcontraron(Contacto contacto, DatosMascotaPerdida datos) {
        this.persona.notificarContactos(this, contacto, datos);
    }

    public void inicializar(MascotaDTO mascota) {
        this.apodo = mascota.apodo;
        this.nombre = mascota.nombre;
        this.edad = mascota.edad;
        this.descripcion = mascota.descripcion;
        this.especie = mascota.especie;
        this.genero = mascota.genero;
        this.caracteristicas = mascota.caracteristicas;
        this.fotos = mascota.fotos;
        this.persona = mascota.persona;
    }

    public List<Foto> redimensionarFotos(List<Foto> fotosOriginales) {
        fotosOriginales.forEach(foto -> foto.editarFoto());
        return fotosOriginales;
    }

    public void meQuiereAdoptar(Persona adoptante) {
        this.persona.notificarPosibleAdopcion(this, adoptante);
    }


    /////Aca comienza el DTO/////
    public static class MascotaDTO {
        private String nombre;
        private String apodo;
        private static Integer idMascota = 0;
        private String descripcion;
        private Integer edad;
        private String especie;
        private String genero;
        private List<CaracteristicaConRta> caracteristicas;
        private List<Foto> fotos;
        private Persona persona;

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

        public static Integer getIdMascota() {
            return idMascota;
        }

        public static void setIdMascota(Integer idMascota) {
            MascotaDTO.idMascota = idMascota;
        }

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

        public List<CaracteristicaConRta> getCaracteristicas() {
            return caracteristicas;
        }

        public void setCaracteristicas(List<CaracteristicaConRta> caracteristicas) {
            this.caracteristicas = caracteristicas;
        }

        public List<Foto> getFotos() {
            return fotos;
        }

        public void setFotos(List<Foto> fotos) {
            this.fotos = fotos;
        }

        public Persona getPersona() {
            return persona;
        }

        public void setPersona(Persona persona) {
            this.persona = persona;
        }

        public void inicializar(Persona persona, String nombre, String apodo, Integer edad, String descripcion,
                                String especie, String genero, List<CaracteristicaConRta> caracteristicas,
                                List<Foto> fotos) {
            this.apodo = apodo;
            this.nombre = nombre;
            this.edad = edad;
            this.descripcion = descripcion;
            this.especie = especie;
            this.genero = genero;
            this.caracteristicas = caracteristicas;
            this.fotos = fotos;
            this.persona = persona;
        }
    }

}
