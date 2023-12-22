import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.ResponseMensaje;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.WhatsAppSender;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestWhtsApp {

//    @Test
//    public void enviarMensaje() throws IOException, UnirestException {
//        Mensaje mensaje = new Mensaje("+541133813676", "ESE MENSJAE ES DEL TP");
//        WhatsAppSender mensajero = new WhatsAppSender();
//        mensajero.enviarMensaje(miembro., mensaje);
//    }
    @Test
    public void enviarMensaje2() throws IOException, UnirestException {
        String wassenger_baseURL="https://api.wassenger.com/v1/messages";
        String wassenger_token="5035a0eba3cd5a88bb2d62b625ba9ea057d1b2c4449ecb1e04ed8dc41d999e06cc85d7c81160267cz";

        String body = "{\"phone\":\"" +"+541138157280" +"\",\"message\":\""+"hola"+"\"}";

        HttpResponse<String> response = Unirest.post(wassenger_baseURL)
                .header("Content-Type", "application/json")
                .header("Token", wassenger_token)
                .body(body)
                .asString();
//

        Gson gson=new Gson();
        ResponseMensaje responseMensaje=gson.fromJson(response.getBody(),ResponseMensaje.class);

        System.out.println(response);
    }
}
