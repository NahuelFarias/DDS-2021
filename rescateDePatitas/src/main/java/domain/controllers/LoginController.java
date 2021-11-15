package domain.controllers;

import domain.models.entities.personas.Persona;
import domain.models.entities.personas.Usuario;
import domain.models.entities.rol.*;
import domain.models.repositories.RepositorioDePersonas;
import domain.models.repositories.RepositorioDeUsuarios;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.daos.DAOHibernate;
import domain.models.repositories.factories.FactoryRepositorio;
import domain.models.repositories.factories.FactoryRepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginController {
    private DAOHibernate daoHibernate = new DAOHibernate();

    private Usuario usuarioHasheador;
    private RepositorioDePersonas repoPersonas = new RepositorioDePersonas(daoHibernate);
    private UsuarioController usuarioController = UsuarioController.getInstancia();

    public ModelAndView inicio(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros, "login.hbs");
    }

    public Response login(Request request, Response response) {
        try {
            RepositorioDeUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();

            String nombreDeUsuario = request.queryParams("nombreDeUsuario");
            String contraseniaBase = request.queryParams("contrasenia");
            String hashed_password = org.apache.commons.codec.digest.DigestUtils.md5Hex(contraseniaBase);

            if (repoUsuarios.existe(nombreDeUsuario) && hashed_password.equals(
                    repoUsuarios.dameElUsuario(nombreDeUsuario).getContrasenia())) {

                Usuario usuario = repoUsuarios.buscarUsuario(nombreDeUsuario);

                request.session(true);
                request.session().attribute("id", usuario.getId());

                response.redirect("/cambiar_rol");
            } else {
                response.redirect("/login");
            }
        } catch (Exception e) {
            //Funcionalidad disponible solo con persistencia en Base de Datos
           response.redirect("/404"); // TODO Cambiar a otra pagina
        }
        finally {
            return response;
        }
    }

    public Response logout(Request request, Response response) {
        request.session().invalidate();
        response.redirect("/");
        return response;
    }

    public ModelAndView mostrarRoles(Request request, Response response) {
        Persona persona = this.repoPersonas.dameLaPersona(request.session().attribute("id"));
        Map<String, Object> parametros = new HashMap<>();
        Set<Rol> roles = persona.getRolesDisponibles();
        parametros.put("roles", roles);
        return new ModelAndView(parametros, "seleccionar_rol.hbs");
    }

    public Response rol_elegido_duenio(Request request, Response response) {
        Persona persona = this.repoPersonas.dameLaPersona(request.session().attribute("id"));
        request.session().attribute("rol", "Duenio");
        persona.setRolElegido(new Duenio());

        response.redirect("/");
        return response;
    }

    public Response rol_elegido_voluntario(Request request, Response response) {
        Persona persona = this.repoPersonas.dameLaPersona(request.session().attribute("id"));
        request.session().attribute("rol", "Voluntario");
        persona.setRolElegido(new Voluntario());
        response.redirect("/");
        return response;
    }

    public Response rol_elegido_rescatista(Request request, Response response) {
        Persona persona = this.repoPersonas.dameLaPersona(request.session().attribute("id"));
        request.session().attribute("rol", "Rescatista");
        persona.setRolElegido(new Rescatista());
        response.redirect("/");
        return response;
    }

    public ModelAndView admin(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);
        Persona persona = this.repoPersonas.dameLaPersona(request.session().attribute("id"));
        parametros.put("persona", persona);

        return new ModelAndView(parametros, "index_admin.hbs");
    }
}
