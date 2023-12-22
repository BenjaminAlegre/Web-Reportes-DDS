package model.entities.comunidad;

import model.entities.servicio.TipoDeBanio;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum MedioNotificacion {

    WHATSAPP("whatsapp"),
    CORREO_ELECTRONICO("correo electronico");



    public final String label;

    private MedioNotificacion(String label) {
        this.label = label;
    }


    public static final Map<String, MedioNotificacion> BY_LABEL = new HashMap<>();

    static {
        for (MedioNotificacion a : values()) {
            BY_LABEL.put(a.label, a);
        }
    }



    public static MedioNotificacion valueOfMedioNotificacion(String label) {
        return BY_LABEL.get(label);
    }

    public static String valorEnString(MedioNotificacion tipo) {
        return tipo.label;
    }


}
