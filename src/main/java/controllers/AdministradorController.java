package controllers;

import controllers.utils.CargadorMasivo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministradorController {


    public ModelAndView pantallaCargaMasiva(Request request, Response response) {
     return new ModelAndView(null, "cargaMasiva.hbs");   
    }

    public Response cargarDatos(Request request, Response response) {
        new CargadorMasivo(request, response).start();
        response.redirect("/aperturaIncidente.hbs");
        return response;
    }



}
