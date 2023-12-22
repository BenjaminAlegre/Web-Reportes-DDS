package DTO;

import lombok.Getter;

@Getter
public class MiembroComunidadDTO {

    private String comunidad;
    private String miembro;

    private String tipoMiembro;



    public MiembroComunidadDTO( String comunidad, String miembro, String tipoMiembro) {
        this.comunidad = comunidad;
        this.miembro = miembro;
        this.tipoMiembro = tipoMiembro;
    }
}
