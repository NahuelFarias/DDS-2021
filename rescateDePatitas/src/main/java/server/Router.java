package server;

import domain.controllers.HomeController;
import domain.controllers.LoginController;
import domain.controllers.MascotaController;
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

    private static void configure(){
        HomeController homeController = new HomeController();
        LoginController loginController = new LoginController();
        MascotaController mascotaController = new MascotaController();
        AuthMiddleware authMiddleware       = new AuthMiddleware();

        Spark.get("/", homeController::mostrarHome, Router.engine);

        Spark.before("/", authMiddleware::verificarSesion);

        Spark.get("/login", loginController::inicio, Router.engine);

        Spark.get("/",loginController::login);

        Spark.get("/logout", loginController::logout);

        Spark.get("/perdidas", mascotaController::mostrarPerdidas, Router.engine);

        Spark.get("/encontradas", mascotaController::mostrarEncontradas, Router.engine);
    }
}
