package model.entities.servicio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter@Getter
@Entity
public class Tramo {

    @Id
    private int idTramo;

    @Enumerated(EnumType.STRING)
    private PuntosTramo puntoOrigen;

    @Enumerated(EnumType.STRING)
    private PuntosTramo puntoFinal;

    public Tramo(PuntosTramo puntoOrigen, PuntosTramo puntoFinal) {

        this.puntoOrigen = puntoOrigen;
        this.puntoFinal = puntoFinal;
    }


    public Tramo() {
    }

    //TODO lo pongo para prueba

    public static String tramo(PuntosTramo inicio , PuntosTramo fin){
        return inicio.label+ " a "+fin.label;
    }
}
