package domain.controllers;

import domain.models.entities.personas.Usuario;
import domain.models.repositories.RepositorioDeUsuarios;
import domain.models.repositories.factories.FactoryRepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private Usuario usuarioHasheador;

    public ModelAndView inicio(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros, "login.hbs");
    }

    public Response login(Request request, Response response) {
        try {
            RepositorioDeUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();

            String nombreDeUsuario = request.queryParams("nombreDeUsuario");
            String contraseniaBase = request.queryParams("contrasenia");
            usuarioHasheador = new Usuario();
            usuarioHasheador.hashPassword(contraseniaBase);

            if (repoUsuarios.existe(nombreDeUsuario) && usuarioHasheador.checkPassword(contraseniaBase,
                    repoUsuarios.dameLaContrasenia(nombreDeUsuario).getContrasenia())) {

                Usuario usuario = repoUsuarios.buscarUsuario(nombreDeUsuario);

                request.session(true);
                request.session().attribute("id", usuario.getId());

                response.redirect("/");
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

}
