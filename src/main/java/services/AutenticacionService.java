package services;

import server.Router;
import spark.Request;
import spark.Response;

import java.util.List;

public class AutenticacionService {

    JWTService jwtService = new JWTService();

    public void authRol (Request req, Response res, List<String> rolesDisponibles){
        System.out.println("Filtro de autenticación");
        if (req.cookie("jwt") == null) {
            res.redirect("/login");
        } else {
            String jwtToken = req.cookie("jwt");
            String jwtPayload = jwtService.decodeJWT(jwtToken);
            String namespace = "http://localhost:3000/";
            String roles = jwtService.obtenerValor(jwtPayload, namespace + "roles");
//                    System.out.println("usser: " + roles);
            if (roles == null || !this.validadorRol(roles, rolesDisponibles)) {
                res.redirect("/login");
            }
        }
    }

    private boolean validadorRol (String rol , List<String> listaRolesDisp){
        return listaRolesDisp.stream().anyMatch( rolEncontrado -> rol.contains(rolEncontrado));
    }
    //request.session().attribute("id");
}
