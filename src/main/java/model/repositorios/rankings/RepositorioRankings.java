package model.repositorios.rankings;

import DTO.PosicionDTO;
import db.EntityManagerHelper;
import model.entities.ranking.PosicionRanking;
import model.entities.ranking.RankImpacto;
import model.entities.ranking.RankStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioRankings {

    public List<PosicionRanking> obtenerRanking(String tipo) {
        RankStrategy ranking = (RankStrategy) EntityManagerHelper.getEntityManager().createQuery("FROM " + RankStrategy.class.getName() +
                " r WHERE tipoRanking = " + tipo +
                " AND r.fecha = (SELECT MAX(rr.fecha) FROM " + RankStrategy.class.getName() + " rr WHERE tipoRanking = " + tipo + ")").getSingleResult();
        System.out.println("************************************************************************* id ranking "+ranking.getId());
        System.out.println(ranking.getPosiciones().size());
        return ranking.getPosiciones();

//        List<RankStrategy> rankings = EntityManagerHelper.getEntityManager().createQuery("from "+ RankStrategy.class.getName()+ " where tipoRanking= "+ tipo).getResultList();
//        System.out.println(rankings.get(rankings.size() -1).getId());
//       return rankings.get(rankings.size() -1).getPosiciones();

    }

//    List<RankStrategy> rankings = EntityManagerHelper.getEntityManager().createQuery("from "+ RankStrategy.class.getName()+ " where tipoRanking= "+ tipo).getResultList();
//
//
public void guardar(RankStrategy rank) {
    EntityManagerHelper.beginTransaction();
    EntityManagerHelper.getEntityManager().persist(rank);
    EntityManagerHelper.commit();
}

    public List<PosicionDTO> obtenerRankingDTO(String tipo) {
        return this.obtenerRanking(tipo).stream().map(p ->p.convertirADTO()).collect(Collectors.toList());
    }

}
