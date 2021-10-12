package domain.controllers;

import domain.models.entities.personas.Usuario;
import domain.models.entities.publicaciones.*;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PublicacionesPerdidasController {
    private RepositorioGenerico<PublicacionPerdidaRegistrada> repo;
    private UsuarioController usuarioController = new UsuarioController();
    private RolController rolController = new RolController();


    public PublicacionesPerdidasController(){
        this.repo = FactoryRepositorio.get(PublicacionPerdidaRegistrada.class);
    }

//    public void crearPublicacion(PublicacionGenerica publicacion) {
//        this.validarDatos();
//        this.repo.agregar(publicacion);
//    }

    private void validarDatos() {
        //TODO
    }

//    public RepositorioGenerico<PublicacionPerdidaRegistrada> getRepo() {
//        return repo;
//    }

    public ModelAndView mostrarPerdidas(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);

        rolController.asignarRolSiEstaLogueado(request, parametros);

        List<PublicacionPerdidaRegistrada> perdidas = new ArrayList<>();
        List<PublicacionPerdidaRegistrada> publicaciones = this.repo.buscarTodos();
        for (PublicacionGenerica publi:publicaciones) {
            if(publi.getTipoPublicacion().equals("Mascota perdida registrada")){
                perdidas.add((PublicacionPerdidaRegistrada) publi);
            }
        }
        parametros.put("perdidas", publicaciones);
        return new ModelAndView(parametros, "perdidas.hbs");
    }

    public ModelAndView revisar_perdida(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);

        rolController.asignarRolSiEstaLogueado(request, parametros);

        List<PublicacionPerdidaRegistrada> encontradas = this.repo.buscarTodos();
        parametros.put("perdidas", encontradas);
        return new ModelAndView(parametros, "revisar_perdida.hbs");
    }

    public ModelAndView revisar_publi(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);

        rolController.asignarRolSiEstaLogueado(request, parametros);

        PublicacionPerdidaRegistrada publicacion = this.repo.buscar(new Integer(request.params("id")));
        parametros.put("publicacion", publicacion);
        return new ModelAndView(parametros, "revisar_perdida_publi.hbs");

    }

    public Response aprobar(Request request, Response response) {
        PublicacionPerdidaRegistrada publicacion = this.repo.buscar(new Integer(request.params("id")));
        publicacion.setEstadoDePublicacion(EstadoDePublicacion.ACEPTADO);
        this.repo.modificar(publicacion);

        response.redirect("/revisar_perdida");

        return response;
    }

    public Response rechazar(Request request, Response response) {
        PublicacionPerdidaRegistrada publicacion = this.repo.buscar(new Integer(request.params("id")));
        publicacion.setEstadoDePublicacion(EstadoDePublicacion.RECHAZADO);
        this.repo.modificar(publicacion);

        response.redirect("/revisar_perdida");

        return response;
    }
}
