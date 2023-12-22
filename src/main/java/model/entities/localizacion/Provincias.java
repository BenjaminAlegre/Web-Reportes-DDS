package model.entities.localizacion;

import java.util.HashMap;
import java.util.Map;

public enum Provincias {
    BUENOS_AIRES("Buenos Aires"),
    CIUDAD_AUTONOMA_DE_BUENOS_AIRES("Ciudad Autónoma de Buenos Aires"),
    CATAMARCA("Catamarca"),
    CHACO("Chaco"),
    CHUBUT("Chubut"),
    CORDOBA("Córdoba"),
    CORRIENTES("Corrientes"),
    ENTRE_RIOS("Entre Ríos"),
    FORMOSA("Formosa"),
    JUJUY("Jujuy"),
    LA_PAMPA("La Pampa"),
    LA_RIOJA("La Rioja"),
    MENDOZA("Mendoza"),
    MISIONES("Misiones"),
    NEUQUEN("Neuquén"),
    RIO_NEGRO("Río Negro"),
    SALTA("Salta"),
    SAN_JUAN("San Juan"),
    SAN_LUIS("San Luis"),
    SANTA_CRUZ("Santa Cruz"),
    SANTA_FE("Santa Fe"),
    SANTIAGO_DEL_ESTERO("Santiago del Estero"),
    TIERRA_DEL_FUEGO_E_ISLAS_DEL_ATLANTICO_SUR("Tierra del Fuego, Antártida e Islas del Atlántico Sur"),
    TUCUMAN("Tucumán");


    public final String label;

    private Provincias(String label) {
        this.label = label;
    }


    public static final Map<String, Provincias> BY_LABEL = new HashMap<>();

    static {
        for (Provincias a : values()) {
            BY_LABEL.put(a.label, a);
        }
    }

    public static Provincias valueOfProvincia(String label) {
        return BY_LABEL.get(label);
    }

    public static String valorEnString(Provincias provincia) {
        return provincia.label;
    }





}
