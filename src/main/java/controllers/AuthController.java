package controllers;



import com.sun.org.apache.bcel.internal.generic.NEW;
import model.repositorios.RepositorioMiembros;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import services.JWTService;
import services.RequestService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Base64;


public class AuthController {

    RequestService requestService = new RequestService();
    JWTService jwtService = new JWTService();
    private String domain = "https://dev-7yecyva5welnz3zc.us.auth0.com";;
    RepositorioMiembros repoMiembros = new RepositorioMiembros();

    public AuthController() {
    }

    public ModelAndView pantallaDeLogin(Request req, Response res) throws Exception {
        String authCode = req.queryParams("code");
        String urlToken = this.domain + "/oauth/token";
        String clientSecret = "Dxiw4FnGlefSrA-yipHyR9dgy7fDhEBK0N5L9-n6V87UUSmspsNDDlgMs-X2MTDH";
        String namespace = "http://localhost:3000/";

        String json = requestService.makePostRequest(urlToken, authCode, clientSecret);
        System.out.println("Json Token: " + json);
        String id_token = jwtService.obtenerValor(json, "id_token");
        System.out.println("idToken: " + id_token);
        String jwtToken = jwtService.decodeJWT(id_token);
        System.out.println("JWT Token: " + jwtToken);


        req.session().attribute("idMiembro", repoMiembros.buscarPorEmail(jwtService.obtenerValor(jwtToken, "name")).getId());
        System.out.println("Id miembrooooooo: " + req.session().attribute("idMiembro"));
        res.cookie("jwt", id_token);
        String roles = jwtService.obtenerValor(jwtToken, namespace + "roles");

        res.redirect("/paginaPrincipal" + getHome(roles));
        return null;
    }

    private String getHome(String rol){
        if(rol.contains("administrador")){
            return "/administrador";
        }else if(rol.contains("miembro")){
            return "/miembro";
        }else if(rol.contains("entidad")){
            return "/entidad";
        } else {
            throw new RuntimeException("No se encontró el rol");
        }

    }

    public ModelAndView logout(Request req, Response res) {
        String clientId = "88BuFrmFnU78LT4FnfGV1Ml4MlMfdKxC";
        String logoutUrl = this.domain + "/v2/logout";
        String returnTo = "http://localhost:3000/inicio";
        String fullLogoutUrl =  logoutUrl + "?returnTo=" + returnTo + "&client_id=" + clientId;

        req.session().removeAttribute("idMiembro");
        res.removeCookie("jwt");
        res.redirect(fullLogoutUrl);
        return null;
    }



}