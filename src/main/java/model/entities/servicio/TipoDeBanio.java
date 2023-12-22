package model.entities.servicio;


import model.entities.localizacion.Provincias;

import java.util.HashMap;
import java.util.Map;

public enum TipoDeBanio {
    HOMBRES("Hombres"),
    MUJERES("Mujeres"),
    SIN_GENERO("Sin Genero"),
    DISCAPACITADOS("Discapacitados");



    public final String label;

    private TipoDeBanio(String label) {
        this.label = label;
    }


    public static final Map<String, TipoDeBanio> BY_LABEL = new HashMap<>();

    static {
        for (TipoDeBanio a : values()) {
            BY_LABEL.put(a.label, a);
        }
    }



    public static TipoDeBanio valueOfTipoDeBanio(String label) {
        return BY_LABEL.get(label);
    }

    public static String valorEnString(TipoDeBanio tipo) {
        return tipo.label;
    }

}
