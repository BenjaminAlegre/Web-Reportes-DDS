package model.entities.comunidad;

import java.util.HashMap;
import java.util.Map;

public enum Rol {
    USUARIO("USUARIO"),
    ADMINSTRADOR ("ADMINISTRADOR");
    public final String label;

    private Rol(String label){
        this.label = label;
    }



    public static final Map<String, Rol> BY_LABEL = new HashMap<>();
    static {
        for (Rol t : values()) {
            BY_LABEL.put(t.label, t);
        }
    }

    public static Rol valueOfTipo( String label){
        return BY_LABEL.get(label);
    }
}
