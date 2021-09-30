package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;


public class HomeController {

    public ModelAndView mostrarHome(Request request, Response response) {

        return new ModelAndView(new HashMap<>(), "index.hbs");
    }


}
