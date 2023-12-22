package DTO;

import lombok.Getter;
import model.entities.servicio.Monitoreable;

@Getter
public class MonitoreableDTO {

    private Integer id;
    private String nombre;

    public MonitoreableDTO(Monitoreable monitoreable) {
        this.id = monitoreable.getId();
        this.nombre = monitoreable.tipo();
    }
}
