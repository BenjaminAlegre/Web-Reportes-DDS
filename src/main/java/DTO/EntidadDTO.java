package DTO;

import lombok.Getter;
import model.entities.entidades.Entidad;

@Getter
public class EntidadDTO {

    private Integer id;
    private String nombre;

    public EntidadDTO(Entidad entidad) {
        this.id = entidad.getId();
        this.nombre = entidad.getNombre();
    }
}
