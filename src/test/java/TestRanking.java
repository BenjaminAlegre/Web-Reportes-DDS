import controllers.RankingsController;
import model.entities.comunidad.Comunidad;
import model.entities.entidades.Entidad;
import model.entities.notificacion.Incidente;
import model.entities.ranking.PosicionRanking;
import model.entities.ranking.Rankeador;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioEntidades;
import model.repositorios.RepositorioServicios;
import model.repositorios.incidentes.RepositorioIncidentes;
import model.repositorios.rankings.RepositorioRankings;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRanking {

    @Test
    public void generarTest(){
        Rankeador rankeador= new Rankeador();
        List<Entidad> entidades = rankeador.repositorioEntidades.buscarTodos();
        rankeador.generarRankings(entidades);
    }

    @Test
    public void trearRanking(){
        RepositorioRankings repositorioRankings = new RepositorioRankings();
        List<PosicionRanking> posiciones = repositorioRankings.obtenerRanking("RankCantidadIncidentes");
        for (PosicionRanking p:posiciones
             ) {
            System.out.println(p.getPosicion()+" "+p.getEntidad().getNombre());
        }

    }

//    @Test
//    public void cargarIncidenteAComunidad(){
//
//        RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
//        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
//
//        Comunidad comunidad = repositorioComunidades.buscarPorId(1);
//        Incidente incidente = repositorioIncidentes.buscarPorId(1);
//        Incidente incidente2 = repositorioIncidentes.buscarPorId(2);
//        Incidente incidente3 = repositorioIncidentes.buscarPorId(3);
//        Incidente incidente4 = repositorioIncidentes.buscarPorId(4);
//        Incidente incidente5 = repositorioIncidentes.buscarPorId(5);
//        List<Incidente> incidentes = new ArrayList<>();
//        incidentes.add(incidente);
//        incidentes.add(incidente2);
//        incidentes.add(incidente3);
//        incidentes.add(incidente4);
//        incidentes.add(incidente5);
//        comunidad.agregarIncidentes(incidentes);
//        repositorioComunidades.actualizar(comunidad);
//
//        Comunidad comunidad2 = repositorioComunidades.buscarPorId(2);
//        Incidente incidente6 = repositorioIncidentes.buscarPorId(6);
//        Incidente incidente7 = repositorioIncidentes.buscarPorId(7);
//        Incidente incidente8 = repositorioIncidentes.buscarPorId(8);
//        Incidente incidente9 = repositorioIncidentes.buscarPorId(9);
//        Incidente incidente10 = repositorioIncidentes.buscarPorId(10);
//        List<Incidente> incidentes2 = new ArrayList<>();
//        incidentes2.add(incidente6);
//        incidentes2.add(incidente7);
//        incidentes2.add(incidente8);
//        incidentes2.add(incidente9);
//        incidentes2.add(incidente10);
//        comunidad2.agregarIncidentes(incidentes2);
//        repositorioComunidades.actualizar(comunidad2);
//
//        Comunidad comunidad3 = repositorioComunidades.buscarPorId(3);
//        Incidente incidente11 = repositorioIncidentes.buscarPorId(11);
//        Incidente incidente12 = repositorioIncidentes.buscarPorId(12);
//        Incidente incidente13 = repositorioIncidentes.buscarPorId(13);
//        Incidente incidente14 = repositorioIncidentes.buscarPorId(14);
//        Incidente incidente15 = repositorioIncidentes.buscarPorId(15);
//        List<Incidente> incidentes3 = new ArrayList<>();
//        incidentes3.add(incidente11);
//        incidentes3.add(incidente12);
//        incidentes3.add(incidente13);
//        incidentes3.add(incidente14);
//        incidentes3.add(incidente15);
//        comunidad3.agregarIncidentes(incidentes3);
//        repositorioComunidades.actualizar(comunidad3);
//
//        Comunidad comunidad4 = repositorioComunidades.buscarPorId(4);
//        Incidente incidente16 = repositorioIncidentes.buscarPorId(16);
//        List<Incidente> incidentes4 = new ArrayList<>();
//        incidentes4.add(incidente16);
//        comunidad4.agregarIncidentes(incidentes4);
//        repositorioComunidades.actualizar(comunidad4);
//    }
}
