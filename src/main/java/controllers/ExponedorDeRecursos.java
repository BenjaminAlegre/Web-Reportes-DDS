package controllers;

import com.google.gson.Gson;
import spark.Response;

import java.util.List;

public class ExponedorDeRecursos {

    public static String exponerRecurso(List<Object> listado, Response response){
        Gson gson = new Gson();
        String inicio = "{\"valores\":";
        String body = gson.toJson(listado);
        String aMandar = inicio + body +"}";
        response.body(aMandar);
        return aMandar;
    }
}
