package server;

import domain.controllers.*;
import domain.models.middleware.AuthMiddleware;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure() {
        HomeController homeController = new HomeController();
        LoginController loginController = new LoginController();
        MascotaController mascotaController = new MascotaController();
        PublicacionesPerdidasController perdidas = new PublicacionesPerdidasController();
        PublicacionesEncontradasController encontradas = new PublicacionesEncontradasController();
        PublicacionesEnAdopcionController enAdopcion = new PublicacionesEnAdopcionController();
        UsuarioController usuarios = UsuarioController.getInstancia();
        AuthMiddleware authMiddleware = new AuthMiddleware();
        AdminController adminController = new AdminController();
        OrganizacionController organizacionController = new OrganizacionController();

        //File uploadDir = new File("/resources/fotosMascotas");
        //uploadDir.mkdir(); //Crea el directorio si no existe

        Spark.get("/", homeController::mostrarHome, Router.engine);

        Spark.before("/", authMiddleware::verificarSesion);

        Spark.get("/login", loginController::inicio, Router.engine);

        Spark.post("/login", loginController::login);

        Spark.get("/logout", loginController::logout);

        Spark.get("/perdidas", perdidas::mostrarPerdidas, Router.engine);

        Spark.get("/encontradas", encontradas::mostrarEncontradas, Router.engine);

        Spark.get("/en_adopcion", enAdopcion::mostrarEnAdopcion, Router.engine);

        Spark.get("/en_adopcion/ok", enAdopcion::mostrarOk, Router.engine);

        Spark.get("/en_adopcion/:id", enAdopcion::mostrarPublicacionEnAdopcion, Router.engine);

        Spark.post("/en_adopcion/:id", enAdopcion::quieroAdoptarlo);

        Spark.get("/elegir_asociacion", mascotaController::registroMascotaAsoc, Router.engine);

        Spark.post("/elegir_asociacion", mascotaController::guardarAsociacion);

        Spark.get("/nueva_mascota", mascotaController::registroMascota, Router.engine);

        Spark.post("/nueva_mascota", mascotaController::guardarMascota);

        Spark.get("/dar_adopcion_asociacion", enAdopcion::mostrarCuestionarioAsociacionAdopcion, Router.engine);

        Spark.post("/dar_adopcion_asociacion", enAdopcion::guardarPublicacion);

        Spark.get("/dar_adopcion", enAdopcion::mostrarCuestionarioAdopcion, Router.engine);

        Spark.post("/dar_adopcion", enAdopcion::recibirDatosCuestionarioDarEnAdopcion);


        /*        (req, res) -> {
            Path tempFile = Files.createTempFile(uploadDir.toPath(), req.queryParams("nombre"), "img");

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            try (InputStream input = req.raw().getPart("foto").getInputStream()) {
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }
            enAdopcion.recibirDatosCuestionarioDarEnAdopcion(req, res);
            return "File uploaded";
        });*/




        Spark.get("/cambiar_rol", loginController::mostrarRoles, Router.engine);

        Spark.get("/rol_elegido_duenio", loginController::rol_elegido_duenio);

        Spark.get("/rol_elegido_voluntario", loginController::rol_elegido_voluntario);

        Spark.get("/rol_elegido_rescatista", loginController::rol_elegido_rescatista);

        Spark.get("/admin", loginController::admin, Router.engine);

        Spark.get("/agregar_pregunta", adminController::admin_preguntas, Router.engine);

        Spark.post("/nueva_pregunta", adminController::nueva_pregunta);

        Spark.get("/revisar_encontrada", encontradas::revisar_encontrada, Router.engine);

        Spark.get("/revisar_encontrada/:id", encontradas::revisar_publi, Router.engine);

        Spark.get("/aprobar_encontrada/:id", encontradas::aprobar);

        Spark.get("/rechazar_encontrada/:id", encontradas::rechazar);

        Spark.get("/revisar_perdida", perdidas::revisar_perdida, Router.engine);

        Spark.get("/revisar_perdida/:id", perdidas::revisar_publi, Router.engine);

        Spark.get("/aprobar_perdida/:id", perdidas::aprobar);

        Spark.get("/rechazar_perdida/:id", perdidas::rechazar);

        Spark.get("/revisar_adopcion", enAdopcion::revisar_adopcion, Router.engine);

        Spark.get("/revisar_adopcion/:id", enAdopcion::revisar_publi, Router.engine);

        Spark.get("/aprobar_adopcion/:id", enAdopcion::aprobar);

        Spark.get("/rechazar_adopcion/:id", enAdopcion::rechazar);

        Spark.get("/ok", mascotaController::creada, Router.engine);

        //Registro mascota encontrada
        Spark.get("/registro_encontrada", encontradas::encontrada, Router.engine);

        Spark.post("/registro_encontrada", encontradas::guardarEncontrada);

        Spark.get("/encontrada_hogares", encontradas::mostrarHogares, Router.engine);

        Spark.get("/publicacion_enviada", encontradas::publicacionEnviada, Router.engine);

        Spark.get("/sin_hogares", encontradas::sinHogares, Router.engine);

        //Registro usuario
        Spark.get("/registro_usuario", usuarios::crearUsuario, Router.engine);

        Spark.post("/registro_usuario", usuarios::validarUsuario);

        Spark.get("/registro_usuario/error_usr", usuarios::crearUsuarioError, Router.engine);

        Spark.get("/registro_usuario_datos", usuarios::ingresarDatosUsuario, Router.engine);

        Spark.post("/registro_usuario_datos", usuarios::cargarDatosUsuario);

        Spark.get("/usuario_creado", usuarios::usuarioCreado, Router.engine);


        //Registrar mascota perdida
        Spark.get("/registro_perdida", perdidas::registrarPerdida, Router.engine);

        Spark.post("/registrar_perdida", perdidas::guardarPerdida);

        //Encontré a mi mascota en una publicacion
        Spark.get("/encontradas/:id", encontradas::mostrarPublicacionEncontrada, Router.engine);

        //si el usuario no esta log
        Spark.post("/enviar_datos/:id", encontradas::enviarDatos);

        //Si el usuario esta log
        Spark.get("/enviar_datos/:id", encontradas::enviarDatosLog);

        Spark.get("/datos_enviados", encontradas::datosEnviados, Router.engine);

        //Mis mascotas
        Spark.get("/mis_mascotas", usuarios::mostrasMisMascotas, Router.engine);

        //Encontre una mascota con QR
        Spark.get("/mascota_encontrada/:id", encontradas::mascotaEncontradaQR, Router.engine);

        Spark.post("/mascota_encontrada", encontradas::enviarMensajeEncontradaQR);


        //Para crear una Asociacion
        Spark.get("/admin_nueva_asociacion", loginController::cuestionarioNuevaOrg,Router.engine);

        Spark.post("/admin_nueva_asociacion", organizacionController::crearOrganizacion);

        //Para agregar preguntas a una Asociacion
        Spark.get("/admin_preguntas_asociacion", organizacionController::cuestionarioAgregarPregunta,Router.engine);

        Spark.post("/admin_preguntas_asociacion", organizacionController::agregarPregunta);

        //Para agregar una Caracteristica General
        //Spark.get("/admin_caracteristica", ,Router.engine);

        //Spark.post("/admin_caracteristica", );


        //Dimensiones fotos


    }
}
