package db.middlewares;

import spark.Request;
import spark.Response;

public class AuthMiddleware {

    public static Response verificarSesion(Request request, Response response) {
        if(request.session().isNew() || request.session().attribute("id") == null) {
            response.redirect("/sin_sesion");
        }
        return response;
    }
}
