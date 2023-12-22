package DTO;

import lombok.Getter;

@Getter
public class ComunidadDTO {

    private Integer id;

    private String nombre;

    public ComunidadDTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
