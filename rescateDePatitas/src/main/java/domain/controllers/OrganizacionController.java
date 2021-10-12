package domain.controllers;

import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.publicaciones.Pregunta;
import domain.models.repositories.RepositorioDeCaracteristicas;
import domain.models.repositories.RepositorioDeOrganizaciones;
import domain.models.repositories.RepositorioDePreguntas;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.daos.DAO;
import domain.models.repositories.daos.DAOHibernate;

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


}
