package model.entities.servicio;

import java.util.HashMap;
import java.util.Map;

public enum TipoDeElevacion {
    ASCENSOR("Ascensor"),
    ESCALERA_MECANICA("Escalera Mecánica");

    public final String label;

    private TipoDeElevacion(String label) {
        this.label = label;
    }


    public static final Map<String,  TipoDeElevacion> BY_LABEL = new HashMap<>();

    static {
        for ( TipoDeElevacion a : values()) {
            BY_LABEL.put(a.label, a);
        }
    }



    public static  TipoDeElevacion valueOfTipoDeElevacion(String label) {
        return BY_LABEL.get(label);
    }

    public static String valorEnString( TipoDeElevacion tipo) {
        return tipo.label;
    }
}
