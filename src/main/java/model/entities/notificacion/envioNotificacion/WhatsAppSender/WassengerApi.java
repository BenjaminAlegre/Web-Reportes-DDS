package model.entities.notificacion.envioNotificacion.WhatsAppSender;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.ResponseMensaje;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  class WassengerApi {
    Properties propiedades=  new Properties();

    public WassengerApi() throws IOException {
            InputStream lectura= new FileInputStream("src\\main\\resources\\configuracion.properties");
            propiedades.load(lectura);

    }

        public ResponseMensaje enviarMensaje(Mensaje mensaje) throws UnirestException,IOException {
        String wassenger_baseURL=propiedades.getProperty("wassenger_baseURL");
        String wassenger_token=propiedades.getProperty("wassenger_token");

        String body = "{\"phone\":\"" +mensaje.getDestinatario() +"\",\"message\":\""+mensaje.getMensaje()+"\"}";
           // System.out.println("{\"phone\":\"" +mensaje.getNumeroTelefono() +"\",\"message\":\""+mensaje.getMensaje()+"\"}"+"+!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        HttpResponse<String> response = Unirest.post(wassenger_baseURL)
                .header("Content-Type", "application/json")
                .header("Token", wassenger_token)
                .body(body)
                .asString();

            System.out.println(response.getBody()+ " " + response);
//

       Gson gson=new Gson();
        ResponseMensaje responseMensaje=gson.fromJson(response.getBody(),ResponseMensaje.class);

        return responseMensaje;
    }



}
