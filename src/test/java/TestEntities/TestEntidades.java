package TestEntities;

import controllers.ExponedorDeRecursos;
import model.entities.comunidad.Comunidad;
import model.entities.entidades.*;
import model.entities.notificacion.Incidente;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioEntidades;
import model.repositorios.incidentes.RepositorioIncidentes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static model.entities.notificacion.EstadoIncidente.ACTIVO;

public class TestEntidades {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();

    private Incidente incidente;


    @Test
    public void testEntraEnCalculoSemanal() {
        Entidad entidad = repositorioEntidades.buscarPorId(1);
        List<Incidente> incidentes = repositorioIncidentes.buscarIncidentesPorEntidad(entidad);

        incidente = incidentes.get(0);

        // Prueba el método entraEnCalculoSemanal con una fecha actual
        LocalDate fechaActual = LocalDate.now().minusDays(1);
        boolean result = incidente.entraEnCalculoSemanal(fechaActual);

        // El incidente esté activo y dentro de la fecha actual
        Assertions.assertTrue(result);
    }

    @Test
    public void testObtenerIncidentes(){
        Entidad entidad = repositorioEntidades.buscarPorId(1);
        List<Incidente> incidentes = repositorioIncidentes.buscarIncidentesPorEntidad(entidad);

        Assertions.assertEquals(incidentes.size(), 1);
    }

    @Test
    public void testTiempoDeCierre() {
        // Obtener Incidente de la BD
        Entidad entidad = repositorioEntidades.buscarPorId(1);
        List<Incidente> incidentes = repositorioIncidentes.buscarIncidentesPorEntidad(entidad);
        incidente = incidentes.get(0);

        // Prueba el método tiempoDeCierre y verifica el resultado
        double tiempoCierre = incidente.tiempoDeCierre();

        // Esto da -5?
        Assertions.assertEquals(-5, tiempoCierre, 0.001);
    }

    @Test
    public void testReportableAMiembro() {
        //TODO
    }

    @Test
    public void traerEntidadesPorTipo(){
        List<Entidad> entidades = repositorioEntidades.buscarPorTipo("" +
                "BANCO");
        for (Entidad e: entidades
             ) {
            System.out.println(e.getNombre());
        }
    }

    @Test
    public void trearOrgYSucursales(){
        Organizacion entidad = (Organizacion) repositorioEntidades.buscarPorTipo("SUPERMERCADO").get(0);// solo tomo 1
        List<Sucursal> sucursales = entidad.getSucursales();
        for (Sucursal s: sucursales
             ) {
            System.out.println(s.getNombre());
        }

    }

    @Test
    public void traerLineaYEstaciones(){
        LineaDeTransporte linea = (LineaDeTransporte) repositorioEntidades.buscarPorTipo("SUBTE").get(0);
        for (Estacion e: linea.getEstaciones()
             ) {
            System.out.println(e.getNombre());
        }
    }

    @Test
    public void traerEstablecimientosPorIdEntidad(){
        Entidad entidad = repositorioEntidades.buscarPorId(7);
        System.out.println(entidad.getEstablecimientos());
    }

    @Test
    public void buscarComunidadPorNombre()
    {
        RepositorioComunidades repo = new RepositorioComunidades();
        Comunidad comunidad = repo.buscarPorNombre("Baños de Subtes");
        System.out.println(comunidad.getNombre()+" "+ comunidad.getId());
    }
}
