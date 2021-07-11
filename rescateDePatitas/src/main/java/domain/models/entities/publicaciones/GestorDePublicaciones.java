package domain.models.entities.publicaciones;

import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Lugar;
import domain.models.entities.mascotas.Mascota;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.personas.Persona;

import java.util.Date;
import java.util.List;

public class GestorDePublicaciones {
    private static GestorDePublicaciones instancia;
    private List<PublicacionGenerica> publicaciones;
    private List<String> preguntasAdopcion;
    private List<Organizacion> organizaciones;

    public static GestorDePublicaciones getInstancia() {
        if (instancia == null) {
            instancia = new GestorDePublicaciones();
        }
        return instancia;
    }

    public Organizacion buscarOrganizacionMasCercana(Lugar lugar){
        //TODO
        return organizaciones.get(0);
    }

    public void generarPublicacionEnAdopcion(Mascota mascota, List<PreguntaAdopcion> pregOrganizacion,
                                             List<PreguntaAdopcion> pregGenerales,Organizacion organizacion){
        PublicacionEnAdopcion publicacion = new PublicacionEnAdopcion();
        publicacion.setFecha(new Date());
        publicacion.setPreguntasOrganizacion(pregOrganizacion);
        publicacion.setPreguntasGenerales(pregGenerales);
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

    public void generarPublicacionPerdidaNoRegistrada(List<Foto> fotos, String descripcion, Lugar lugar){
        PublicacionPerdidaNoRegistrada publicacion = new PublicacionPerdidaNoRegistrada();
        publicacion.setEstadoDePublicacion(EstadoDePublicacion.SIN_REVISAR);
        publicacion.setDescripcion(descripcion);
        publicacion.setFotos(fotos);
        publicacion.setLugar(lugar);
        publicacion.setFecha(new Date());
        Organizacion organizacion = this.buscarOrganizacionMasCercana(lugar);
        publicacion.setOrganizacion(organizacion);

        publicaciones.add(publicacion);

        //Datos Publicacion:
        //-Fecha
        //-Fotos
        //-Descripcion de como la encontro
        //-Lugar (seleccionable a traves de un mapa) Pongo un string por el momento
        //-Asignar organizacion mas cercana

    }

    public void generarPublicacionIntencionAdoptar(Persona adoptante, List<PreguntaAdopcion> preguntasGenerales){
        //TODO Revisar lo de las preguntas
        PublicacionIntencionAdoptar publicacion = new PublicacionIntencionAdoptar();
        publicacion.setAdoptante(adoptante);
        publicacion.setPreferenciasYcomodidades(preguntasGenerales);
    }

    public void generarRecomendacionesSemanales(){

    }



}