package controllers;

import DTO.PosicionDTO;
import model.entities.ranking.PosicionRanking;
import model.repositorios.rankings.RepositorioRankings;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RankingsController {

    RepositorioRankings repositorioRankings = new RepositorioRankings();

    public ModelAndView pantallaRankings(Request req, Response res){
       return new ModelAndView(null, "rankings.hbs");
    }

    public String rakingCantidadIncidentes(Request request, Response response){
        return null;
    }

    public String rakingTiempoDeCierre(Request request, Response response){
        return null;
    }
    public String rakingImpacto(Request request, Response response){
        return null;
    }

    public ModelAndView mostrarRanking(Request request, Response response) {
        String tipo = request.queryParams("tipo");
        System.out.println("//////////////////////////////////////////////////////////////////////"+tipo);
        List<PosicionDTO> posiciones = repositorioRankings.obtenerRankingDTO(tipo);
        return new ModelAndView(new HashMap<String, Object>() {{
            put("posicion", posiciones);
            put("tipoRanking", tipo );
        }},"mostrarRanking.hbs");
    }

    public String enviarRanking(Request request, Response response) {
        String tipo = request.queryParams("tipo");
        List<PosicionRanking> posiciones = repositorioRankings.obtenerRanking(tipo);
        System.out.println("//////////////////////////////////////////////////////////////////////"+tipo);
        return ExponedorDeRecursos.exponerRecurso( posiciones.stream().map(p ->p.convertirADTO()).collect(Collectors.toList()), response);
    }

    public ModelAndView pantallaRankingPesado(Request request, Response response) {
        return new ModelAndView(null, "rankingsPesado.hbs");
    }
}
