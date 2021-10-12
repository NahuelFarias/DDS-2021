package domain.controllers;

import domain.models.entities.personas.Persona;
import domain.models.entities.personas.Usuario;
import domain.models.entities.publicaciones.Pregunta;
import domain.models.repositories.RepositorioDePersonas;
import domain.models.repositories.RepositorioDePreguntas;
import domain.models.repositories.RepositorioGenerico;
import domain.models.repositories.daos.DAO;
import domain.models.repositories.daos.DAOHibernate;
import domain.models.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;


public class PersonaController {
    private RepositorioDePersonas repositorio;
    private static PersonaController instancia;


    public PersonaController(){
        DAO<Persona> dao = new DAOHibernate<>(Persona.class);
        this.repositorio = new RepositorioDePersonas(dao);
    }

    public static PersonaController getInstancia() {
        if (instancia == null) {
            instancia = new PersonaController();
        }
        return instancia;
    }

    public RepositorioDePersonas getRepositorio() {
        return repositorio;
    }


    private void asignarAtributosA(Persona persona, Request request){
        //TODO Setear atributos a la persona
        /*
        String nombre,apellido,email;

        if(request.queryParams("nombre") != null){
            persona.setNombre(request.queryParams("nombre"));
        }

        if(request.queryParams("apellido") != null){
            persona.setApellido(request.queryParams("apellido"));
        }
//fnac, tipo y nro doc, email

        if(request.queryParams("nombreDeUsuario") != null){
            persona.setNombreDeUsuario(request.queryParams("nombreDeUsuario"));
        }

        if(request.queryParams("apellido") != null){
            usuario.setApellido(request.queryParams("apellido"));
        }

        if(request.queryParams("legajo") != null){
            int legajo = new Integer(request.queryParams("legajo"));
            usuario.setLegajo(legajo);
        }

        if(request.queryParams("fechaDeNacimiento") != null && !request.queryParams("fechaDeNacimiento").isEmpty()){
            LocalDate fechaDeNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
            usuario.setFechaDeNacimiento(fechaDeNacimiento);
        }

        if(request.queryParams("rol") != null){
            Repositorio<Rol> repoRol = FactoryRepositorio.get(Rol.class);
            Rol unRol = repoRol.buscar(new Integer(request.queryParams("rol")));
            usuario.setRol(unRol);
        }

         */
    }
/*
    public ModelAndView crear(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Repositorio<Rol> repoRol = FactoryRepositorio.get(Rol.class);

        parametros.put("roles", repoRol.buscarTodos());
        return new ModelAndView(parametros, "usuario.hbs");
    }

    public Response guardar(Request request, Response response){
        Persona persona = new Persona();
        asignarAtributosA(persona, request);
        this.repositorio.agregar(persona);
        response.redirect("/usuarios");
        return response;
    }

    public Response eliminar(Request request, Response response){
        Usuario usuario = this.repo.buscar(new Integer(request.params("id")));
        this.repo.eliminar(usuario);
        return response;
    }
 */
}
