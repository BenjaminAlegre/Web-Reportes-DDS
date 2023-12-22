package model.entities.servicio;

import java.util.HashMap;
import java.util.Map;

public enum PuntosTramo {
    CALLE("Calle"),
    ACCESO_A_TRANSPORTE("Acceso a Transporte"),
    ANDEN("Anden");

    public final String label;

    private PuntosTramo(String label) {
        this.label = label;
    }


    public static final Map<String, PuntosTramo> BY_LABEL = new HashMap<>();

    static {
        for (PuntosTramo a : values()) {
            BY_LABEL.put(a.label, a);
        }
    }



    public static PuntosTramo valueOfPuntosTramo(String label) {
        return BY_LABEL.get(label);
    }

    public static String valorEnString(PuntosTramo tipo) {
        return tipo.label;
    }
}
