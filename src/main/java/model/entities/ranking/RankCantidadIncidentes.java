package model.entities.ranking;


import model.entities.entidades.Entidad;
import model.repositorios.rankings.RepositorioRankings;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class RankCantidadIncidentes extends RankStrategy {

    @Transient
    RepositorioRankings repo = new RepositorioRankings();



    @Override
    protected void rankear(List<Entidad> entidades) {
        List<Entidad> ordenadas = new ArrayList<Entidad>(entidades);
        ordenadas.sort(Comparator.comparing(e->e.cantidadIncidentesSemanales(this.getFecha().minusDays(7))));

        super.genearPosiciones(ordenadas);
        this.repo.guardar(this);
    }

}
