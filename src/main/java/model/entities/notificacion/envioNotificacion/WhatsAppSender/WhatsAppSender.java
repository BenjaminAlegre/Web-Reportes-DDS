package model.entities.notificacion.envioNotificacion.WhatsAppSender;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.Getter;
import model.entities.comunidad.Miembro;
import model.entities.notificacion.envioNotificacion.EstrategiaNotificacion;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.ResponseMensaje;

import java.io.IOException;

@Getter
public class WhatsAppSender extends EstrategiaNotificacion {
    private WassengerApi adapter = new WassengerApi();

    public WhatsAppSender() throws IOException {

    }


//
//    public static WhatsAppSender instancia(){
//        if(instancia== null){
//            instancia = new WhatsAppSender();
//        }
//        return instancia;
//    }
    @Override
    public void enviarMensaje( Mensaje mensaje) throws UnirestException, IOException {

        ResponseMensaje response= this.adapter.enviarMensaje(mensaje);
        if (!response.getStatus().equals("400") ){
        System.out.println("Se envio correctamente el  mensaje:   "+mensaje.getMensaje());
        System.out.println("Al  numero");
        System.out.println(response.getPhone());
        System.out.println("A la hora ");
        System.out.println(response.getDeliverAt());}
        else {
            System.out.println("No se pudo enviar el mensaje");

        }

    }


}
