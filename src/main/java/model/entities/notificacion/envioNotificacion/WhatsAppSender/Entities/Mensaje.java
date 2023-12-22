package model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Mensaje {

    String destinatario = "rube_pages@htmail.com.ar";
    String mensaje;


    public Mensaje (){
    }
    public Mensaje (String destino,String mensajeTexto){
        this.destinatario = destino;
        this.mensaje=mensajeTexto;
    }
}
