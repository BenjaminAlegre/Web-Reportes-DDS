package DTO;

import lombok.Getter;
import model.entities.entidades.Establecimiento;

@Getter
public class EstablecimientoDTO {

    private Integer id;
    private String nombre;

    public EstablecimientoDTO(Establecimiento eestablecimiento) {
        this.id = eestablecimiento.getId();
        this.nombre = eestablecimiento.getNombre();
    }
}
