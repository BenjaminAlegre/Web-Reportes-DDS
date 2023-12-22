package model.entities.ranking;


import lombok.Getter;
import lombok.Setter;
import model.entities.entidades.Entidad;
import model.entities.persistencia.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Rankings")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoRanking")
public abstract class RankStrategy extends EntidadPersistente {


    @Column
    private LocalDate fecha = LocalDate.now();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ranking", cascade = CascadeType.ALL)
    private List<PosicionRanking> posiciones = new ArrayList<>();


    public void generarRanking(List<Entidad> entidades){
        this.rankear(entidades);

    }

    protected abstract void rankear(List<Entidad> entidades);

    protected void genearPosiciones(List<Entidad> ordenadas){
        for (Entidad e:ordenadas
        ) {
            PosicionRanking posicion = new PosicionRanking(this, e, ordenadas.indexOf(e) +1);
            this.posiciones.add(posicion);
        }
    }

}
