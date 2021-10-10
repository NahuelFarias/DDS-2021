package domain.models.middleware;

import spark.Request;
import spark.Response;

public class AuthMiddleware {

    public Response verificarSesion(Request request, Response response){
        if(request.session().attribute("id") == null ){ // Mientras tenga un id no me manda a /login
            //response.redirect("/login");
        }
        return response;
    }
}
