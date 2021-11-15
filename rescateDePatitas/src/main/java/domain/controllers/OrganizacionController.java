package domain.controllers;

import domain.models.entities.mascotas.Lugar;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.publicaciones.Pregunta;
import domain.models.repositories.RepositorioDeCaracteristicas;
import domain.models.repositories.RepositorioDeOrganizaciones;
import domain.models.repositories.RepositorioDePreguntas;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.daos.DAO;
import domain.models.repositories.daos.DAOHibernate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class OrganizacionController {
    private static OrganizacionController instancia;
    private RepositorioDeOrganizaciones repositorio;

    public OrganizacionController(){
        DAO<Organizacion> dao = new DAOHibernate<>(Organizacion.class);
        this.repositorio = new RepositorioDeOrganizaciones(dao);
    }

    public static OrganizacionController getInstancia() {
        if (instancia == null) {
            instancia=new OrganizacionController();
        }
        return instancia;
    }

    public RepositorioDeOrganizaciones getRepositorio() {
        return repositorio;
    }

    public Response crearOrganizacion(Request request, Response response){
        Organizacion organizacion = new Organizacion();
        Lugar ubicacion = new Lugar();

        ubicacion.setLatitud(Double.parseDouble(request.queryParams("latitud")));
        ubicacion.setLongitud(Double.parseDouble(request.queryParams("longitud")));

        organizacion.setUbicacion(ubicacion);
        organizacion.setNombre(request.queryParams(""));

        repositorio.agregar(organizacion);

        response.redirect("/admin");
        return response;
    }

    //public ModelAndView llenarOrganizacion
}
