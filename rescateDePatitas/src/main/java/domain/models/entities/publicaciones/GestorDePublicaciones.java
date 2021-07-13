package domain.models.entities.publicaciones;

import domain.models.entities.mascotas.*;
import domain.models.entities.personas.Persona;
import services.ComparadorDistancias;

import java.util.*;

public class GestorDePublicaciones{
    private static GestorDePublicaciones instancia;
    private List<PublicacionGenerica> publicaciones = new ArrayList<>();
    private List<String> preguntasAdopcion;
    private List<Organizacion> organizaciones = new ArrayList<>();

    public static GestorDePublicaciones getInstancia() {
        if (instancia == null) {
            instancia = new GestorDePublicaciones();
        }
        return instancia;
    }

    public void setOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public List<PublicacionGenerica> getPublicaciones() {
        return publicaciones;
    }


    public Organizacion buscarOrganizacionMasCercana(Lugar lugarEncuentro){
        ComparadorDistancias comparador = new ComparadorDistancias();
        Organizacion organizacion = Collections.min(organizaciones,Comparator.comparing(o->comparador.comparar(o.getUbicacion(),lugarEncuentro)));
        return organizacion;
    }

    public void generarPublicacionEnAdopcion(Mascota mascota, List<RespuestaSobrePregunta> respuestasOrganizacion,
                                             List<RespuestaSobrePregunta> respuestasGenerales,Organizacion organizacion){
        PublicacionEnAdopcion publicacion = new PublicacionEnAdopcion();
        publicacion.setFecha(new Date());
        publicacion.setPreguntasOrganizacion(respuestasOrganizacion);
        publicacion.setPreguntasGenerales(respuestasGenerales);
        publicacion.setPreguntasOrganizacion(respuestasOrganizacion);
        publicacion.setMascota(mascota);
        publicacion.setOrganizacion(organizacion);
        publicacion.setTipoPublicacion("Mascota dada en adopcion");

        publicaciones.add(publicacion);

        //Datos Publicacion:
        //-Fecha
        //-Preguntas Organizacion (O sea que tiene que estar asociada a una organizacion. Elige una?)
        //-Preguntas generales
        //-Datos Mascota

    }

    public void generarPublicacionPerdidaRegistrada(Mascota mascota){
        PublicacionPerdidaRegistrada publicacion = new PublicacionPerdidaRegistrada();
        publicacion.setFecha(new Date());
        publicacion.setMascota(mascota);
        publicacion.setTipoPublicacion("Mascota perdida registrada");
        publicaciones.add(publicacion);

        //Datos Publicacion:
        //-Fecha
        //-Info de la mascota
        //-Estado (aprobada, pendiente, rechazada)

    }

    public void generarPublicacionPerdidaNoRegistrada(Persona rescatista, DatosMascotaPerdida datosMascota){
        PublicacionPerdidaNoRegistrada publicacion = new PublicacionPerdidaNoRegistrada();
        publicacion.setEstadoDePublicacion(EstadoDePublicacion.SIN_REVISAR);
        publicacion.setDescripcion(datosMascota.getDescripcion());
        publicacion.setFotos(datosMascota.getFotos());
        publicacion.setLugar(datosMascota.getLugar());
        publicacion.setFecha(new Date());
        publicacion.setRescatista(rescatista);
        Organizacion organizacion = this.buscarOrganizacionMasCercana(datosMascota.getLugar());
        publicacion.setOrganizacion(organizacion);
        publicacion.setTipoPublicacion("Mascota perdida no registrada");

        publicaciones.add(publicacion);

        //Datos Publicacion:
        //-Fecha
        //-Fotos
        //-Descripcion de como la encontro
        //-Lugar (seleccionable a traves de un mapa)
        //-Asignar organizacion mas cercana

    }

    public void generarPublicacionIntencionAdoptar(Persona adoptante, List<RespuestaSobrePregunta> respuestasGenerales){
        //Pasamos directamente las preguntas
        PublicacionIntencionAdoptar publicacion = new PublicacionIntencionAdoptar();
        publicacion.setAdoptante(adoptante);
        publicacion.setPreferenciasYcomodidades(respuestasGenerales);
        publicacion.setTipoPublicacion("Intencion de adoptar una mascota");


    }

    public void generarRecomendacionesSemanales(){
        //TODO

    }



}