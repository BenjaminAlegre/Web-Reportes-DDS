package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PosicionDTO {

    private String posicion;
    private String nombreEntidad;
    private String cuitEntidad;


    public PosicionDTO(String posicion, String nombreEntidad) {
        this.posicion = posicion;
        this.nombreEntidad = nombreEntidad;
     //   this.cuitEntidad = cuitEntidad;
    }
}
