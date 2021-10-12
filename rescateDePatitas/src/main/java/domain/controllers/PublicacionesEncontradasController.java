package domain.controllers;

import domain.models.entities.publicaciones.EstadoDePublicacion;
import domain.models.entities.publicaciones.PublicacionEnAdopcion;
import domain.models.entities.publicaciones.PublicacionGenerica;
import domain.models.entities.publicaciones.PublicacionMascotaEncontrada;
import domain.models.entities.rol.Rol;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicacionesEncontradasController {
    private RepositorioGenerico<PublicacionMascotaEncontrada> repo;
    private UsuarioController usuarioController = new UsuarioController();
    private RolController rolController = new RolController();


    public PublicacionesEncontradasController(){
       this.repo = FactoryRepositorio.get(PublicacionMascotaEncontrada.class);
    }

    public ModelAndView mostrarEncontradas(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionMascotaEncontrada> encontradas = this.repo.buscarTodos();

        //filtrar por si esta aprobada
//        for (PublicacionGenerica publi:publicaciones) {
//            if(publi.getTipoPublicacion().equals("Mascota perdida no registrada")){
//                encontradas.add((PublicacionMascotaEncontrada) publi);
//            }
//        }
        parametros.put("encontradas", encontradas);
        return new ModelAndView(parametros, "encontradas.hbs");
    }

    public ModelAndView revisar_encontrada(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);

        rolController.asignarRolSiEstaLogueado(request, parametros);

        List<PublicacionMascotaEncontrada> encontradas = this.repo.buscarTodos();
        parametros.put("encontradas", encontradas);
        return new ModelAndView(parametros, "revisar_encontrada.hbs");
    }

    public ModelAndView revisar_publi(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        usuarioController.asignarUsuarioSiEstaLogueado(request, parametros);

        rolController.asignarRolSiEstaLogueado(request, parametros);

        PublicacionMascotaEncontrada publicacion = this.repo.buscar(new Integer(request.params("id")));
        parametros.put("publicacion", publicacion);
        return new ModelAndView(parametros, "revisar_encontrada_publi.hbs");
    }

    public Response aprobar(Request request, Response response) {
        PublicacionMascotaEncontrada publicacion = this.repo.buscar(new Integer(request.params("id")));
        publicacion.setEstadoDePublicacion(EstadoDePublicacion.ACEPTADO);
        this.repo.modificar(publicacion);

        response.redirect("/revisar_encontrada");

        return response;
    }

    public Response rechazar(Request request, Response response) {
        PublicacionMascotaEncontrada publicacion = this.repo.buscar(new Integer(request.params("id")));
        publicacion.setEstadoDePublicacion(EstadoDePublicacion.RECHAZADO);
        this.repo.modificar(publicacion);

        response.redirect("/revisar_encontrada");

        return response;
    }
}
