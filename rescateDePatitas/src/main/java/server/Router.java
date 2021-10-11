package server;

import com.github.jknack.handlebars.Handlebars;
import domain.controllers.*;
import domain.models.middleware.AuthMiddleware;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

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
        AuthMiddleware authMiddleware = new AuthMiddleware();

        Spark.get("/", homeController::mostrarHome, Router.engine);

        Spark.before("/", authMiddleware::verificarSesion);

        Spark.get("/login", loginController::inicio, Router.engine);

        Spark.post("/login", loginController::login);

        Spark.get("/logout", loginController::logout);

        Spark.get("/perdidas", perdidas::mostrarPerdidas, Router.engine);

        Spark.get("/encontradas", encontradas::mostrarEncontradas, Router.engine);

        Spark.get("/en_adopcion", enAdopcion::mostrarEnAdopcion, Router.engine);

        Spark.get("/elegir_asociacion", mascotaController::registroMascotaAsoc, Router.engine);

        Spark.get("/nueva_mascota", mascotaController::registroMascota, Router.engine);

        Spark.post("/nueva_mascota", mascotaController::guardarMascota);

        Spark.get("/dar_adopcion", mascotaController::darEnAdopcion, Router.engine);

        Spark.post("/dar_adopcion", mascotaController::guardarMascota);

        Spark.get("/cambiar_rol", loginController::mostrarRoles, Router.engine);

        Spark.get("/rol_elegido_duenio", loginController::rol_elegido_duenio);

        Spark.get("/rol_elegido_voluntario", loginController::rol_elegido_voluntario);

        Spark.get("/rol_elegido_rescatista", loginController::rol_elegido_rescatista);

        Spark.get("/ok", mascotaController::creada, Router.engine);

    }
}
