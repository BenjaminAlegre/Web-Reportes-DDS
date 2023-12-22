package model.entities.notificacion.envioNotificacion;

import com.mashape.unirest.http.exceptions.UnirestException;
import model.entities.comunidad.Miembro;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public abstract class EstrategiaNotificacion {

    public abstract void enviarMensaje(Mensaje mensaje) throws IOException, UnirestException, MessagingException;
}
