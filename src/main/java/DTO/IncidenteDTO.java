package DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter
public class IncidenteDTO {
    private Integer id;
    private String estado;
    private String observaciones;
    private LocalDateTime horarioApertura;
    private LocalDateTime horarioCierre;




    public IncidenteDTO(Integer id, String estado, String observaciones, LocalDateTime horarioApertura, LocalDateTime horarioCierre) {
        this.id = id;
        this.estado = estado;
        this.observaciones = observaciones;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }
}
