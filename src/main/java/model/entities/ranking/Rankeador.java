package model.entities.ranking;


import model.entities.entidades.Entidad;
import model.repositorios.RepositorioEntidades;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.List;

public class Rankeador implements Job {
    public RepositorioEntidades repositorioEntidades= new RepositorioEntidades();
    public List<RankStrategy> rankingsAGenerar = new ArrayList<>();

    public Rankeador() {
        this.rankingsAGenerar.add(new RankCantidadIncidentes());
        this.rankingsAGenerar.add(new RankTiempoDeCierre());
        this.rankingsAGenerar.add(new RankImpacto());

    }

    public void generarRankings(List<Entidad> entidades){
        for (RankStrategy r: this.rankingsAGenerar
             ) {
            r.generarRanking(entidades);
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Entidad> entidades = repositorioEntidades.buscarTodos();
        generarRankings(entidades);

    }
    //TODO falta logica de generar rankings
}
