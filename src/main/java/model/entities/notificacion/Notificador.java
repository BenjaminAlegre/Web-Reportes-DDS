package model.entities.notificacion;


import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.Getter;
import lombok.Setter;
import model.entities.comunidad.MedioNotificacion;
import model.entities.comunidad.Miembro;
import model.entities.notificacion.envioNotificacion.EmailSender;
import model.entities.notificacion.envioNotificacion.EstrategiaNotificacion;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.WhatsAppSender;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Getter @Setter
public class Notificador {

    public EstrategiaNotificacion estrategiaNotificacion;

    public void encviarMensaje( Mensaje mensaje) throws UnirestException, IOException, MessagingException {
        this.estrategiaNotificacion.enviarMensaje(mensaje);
    }

    public void notificar(String mensaje, Miembro miembro) throws IOException, MessagingException, UnirestException, GeneralSecurityException {
        this.establecerMedioDeNotificacion(miembro.getMedioNotificacion());
        Mensaje aEnviar = this.armarMensaje(miembro, mensaje);
        this.estrategiaNotificacion.enviarMensaje(aEnviar);
    }

    private Mensaje armarMensaje(Miembro miembro, String mensaje) {
        return new Mensaje(miembro.contacto(),mensaje);
    }

    private void establecerMedioDeNotificacion(MedioNotificacion medioNotificacion) throws IOException, GeneralSecurityException {
        switch (medioNotificacion){
            case WHATSAPP: this.estrategiaNotificacion = new WhatsAppSender(); break;
            case CORREO_ELECTRONICO: this.estrategiaNotificacion = new EmailSender();
        }
    }
}
