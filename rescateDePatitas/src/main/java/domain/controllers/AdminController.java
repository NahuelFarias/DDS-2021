package domain.controllers;

import domain.models.entities.personas.Persona;
import domain.models.entities.personas.Usuario;
import domain.models.repositories.RepositorioDePersonas;
import domain.models.repositories.daos.DAOHibernate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class AdminController {
    private DAOHibernate daoHibernate = new DAOHibernate();

    private RepositorioDePersonas repoPersonas = new RepositorioDePersonas(daoHibernate);
    private UsuarioController usuarioController = new UsuarioController();

    public ModelAndView admin_preguntas(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);
        Persona persona = this.repoPersonas.dameLaPersona(request.session().attribute("id"));
        parametros.put("persona", persona);

        return new ModelAndView(parametros, "admin_preguntas.hbs");
    }
}
