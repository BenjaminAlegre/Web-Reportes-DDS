package model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseMensaje {
    String id;
    String user;
    String device;
    String contact;
    String reference;
    String phone;
    String status;
    String deliveryStatus;
    String failureReason;
    String sentAt;
    String processedAt;
    String deliverAt;
    String expiresAt;
    String failedAt;


}
