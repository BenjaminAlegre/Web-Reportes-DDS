import com.mashape.unirest.http.exceptions.UnirestException;
import model.entities.comunidad.Miembro;
import model.entities.notificacion.envioNotificacion.EmailSender;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class TestEmail {

    @Test
    public void enviaMail() throws IOException, MessagingException, UnirestException, GeneralSecurityException {
        EmailSender sender = new EmailSender();

        Mensaje mensaje = new Mensaje("ruben_pages@hotmail.com.ar","hola desde tp");
        sender.enviarMensaje(mensaje);
    }
}
