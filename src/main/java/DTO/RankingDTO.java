package DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class RankingDTO {

    private LocalDate fecha;
    private List<PosicionDTO> posiciones;


    public RankingDTO(LocalDate fecha, List<PosicionDTO> posiciones) {
        this.fecha = fecha;
        this.posiciones = posiciones;
    }
}
