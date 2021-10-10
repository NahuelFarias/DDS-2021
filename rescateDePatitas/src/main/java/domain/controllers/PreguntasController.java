package domain.controllers;

import domain.controllers.exceptions.CaracteristicaSinDescripcionException;
import domain.models.entities.publicaciones.Pregunta;
import domain.models.repositories.RepositorioDeCaracteristicas;
import domain.models.repositories.RepositorioDePreguntas;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.daos.DAO;
import domain.models.repositories.daos.DAOHibernate;
import domain.models.repositories.factories.FactoryRepositorio;

import java.util.List;

public class PreguntasController {
    private static PreguntasController instancia;
    private RepositorioDePreguntas repositorio;

    public PreguntasController(){
        DAO<Pregunta> dao = new DAOHibernate<>(Pregunta.class);
        this.repositorio = new RepositorioDePreguntas(dao);
    }

    public static PreguntasController getInstancia() {
        if (instancia == null) {
            instancia = new PreguntasController();
        }
        return instancia;
    }

    public void crearCaracteristica(String descripcion, List<String> respuestasPosibles,String tipo) {
        this.validarDatos(descripcion, respuestasPosibles);
        Pregunta caracteristica = new Pregunta();
        caracteristica.setPregunta(descripcion);
        caracteristica.setRespuestas(respuestasPosibles);
        caracteristica.setTipoDePregunta(tipo);
        this.repositorio.agregar(caracteristica);
    }

    private void validarDatos(String descripcion, List<String> respuestasPosibles) {
        if (descripcion == null) {
            throw new CaracteristicaSinDescripcionException("La caracteristica no tiene descripcion");
        }
        if (respuestasPosibles == null) {
            throw new CaracteristicaSinDescripcionException("La caracteristica no tiene respuestas posibles");
        }
    }

    public RepositorioDePreguntas getRepositorio() {
        return repositorio;
    }
}
