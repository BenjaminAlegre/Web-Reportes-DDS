package model.entities.notificacion.envioNotificacion;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.Getter;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

@Getter
public class EmailSender extends EstrategiaNotificacion{

        private Properties properties = new Properties();
        private Session session   = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication("tpaddsfrba@gmail.com","noacggvnmoetnur");
                }
        });


        public EmailSender() throws IOException, GeneralSecurityException {
                //InputStream lectura= new FileInputStream("src\\main\\resources\\configuracion.properties");
                //properties.load(lectura);
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");
                properties.put("mail.smtp.auth", true);
                properties.put("mail.smtp.starttls.enable","true");
                MailSSLSocketFactory sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
                // or
                // sf.setTrustedHosts(new String[] { "my-server" });
                properties.put("mail.smtp.ssl.enable", "true");
                // also use following for additional safety
                //props.put("mail.smtp.ssl.checkserveridentity", "true");
                properties.put("mail.smtp.ssl.socketFactory", sf);

        }




        @Override
        public void enviarMensaje(Mensaje mensaje) throws IOException, UnirestException, MessagingException {
              try {
                      Message message = new MimeMessage(session);
                      //message.setFrom(new InternetAddress(properties.getProperty("username")));
                      message.addRecipient(Message.RecipientType.TO, new InternetAddress(mensaje.getDestinatario(), true)); // Recipient's email address
                      message.setSubject("Notificacion Incidente");
                      message.setText(mensaje.getMensaje());
                      session.setDebug(true);

                      Transport.send(message);

                      System.out.println("Email sent successfully!");
              }catch(MessagingException me){
                      System.out.println("Exception: "+me);
              }

        }
}

