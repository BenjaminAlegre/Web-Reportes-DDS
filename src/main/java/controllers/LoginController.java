package controllers;



import spark.ModelAndView;
import spark.Request;
import spark.Response;



public class LoginController {

    private String domain = "https://dev-7yecyva5welnz3zc.us.auth0.com";;

    public LoginController() {
    }

    public ModelAndView pantallaDeLogin(Request req, Response res) {
        String redirectUri = this.domain;
        res.redirect(this.domain + "/authorize" +
                "?response_type=code" +
                "&client_id=88BuFrmFnU78LT4FnfGV1Ml4MlMfdKxC" +
                "&redirect_uri=http://localhost:3000/callback" +
                "&scope=openid%20profile%20email,offline_access,read:current_user,update:current_user_metadata");
        return null;
    }




}

