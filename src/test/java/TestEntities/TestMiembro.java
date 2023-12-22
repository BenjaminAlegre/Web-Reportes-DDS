package TestEntities;

import model.entities.comunidad.*;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioMiembroComunidad;
import model.repositorios.RepositorioMiembros;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestMiembro {


    private static EntityManagerFactory emf; // no hace falta poner esto solo hace falta inportar lña clase EntityManager
    private static EntityManager em;



    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();



    @Test
    public void testVerificarComunidades(){
        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
        List<Comunidad> comunidades = repositorioComunidades.buscarTodos();
        assertEquals(3, comunidades.size());
    }

//FUncionan con properties en update
    @Test
    public void testAgregarMiembroAComunidad(){

        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();

        Miembro m1 = repositorioMiembros.buscarPorId(1);

        Comunidad comunidad2 = repositorioComunidades.buscarPorId(2);
        comunidad2.agregarMiembro(m1);

        repositorioComunidades.agregar(comunidad2);

    }

    @Test
    public void testAgregarAdministradorAComunidad(){

        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();

        Miembro m1 = repositorioMiembros.buscarPorId(3);

        Comunidad comunidad2 = repositorioComunidades.buscarPorId(3);

        comunidad2.agregarAdministrador(m1);

        repositorioComunidades.agregar(comunidad2);
    }

//    @Test//En teoria funciona pero no persiste la relacion
//    public void testAgregarComunidadAMiembro(){
//        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
//        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
//
//        Miembro m1 = repositorioMiembros.buscarPorId(1);
//
//        Comunidad comunidad1 = repositorioComunidades.buscarPorId(1);
//
//        m1.asociarseAComunidad(comunidad1);
//
//        repositorioMiembros.agregar(m1);
//
//        Assertions.assertEquals(comunidad1.getId(), m1.getComunidades().get(0).getId());
//    }

    @Test
    public void testEliminarMiembroDeComunidad(){
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();

        Miembro m1 = repositorioMiembros.buscarPorId(1);
        Comunidad comunidad1 = repositorioComunidades.buscarPorId(1);

        comunidad1.eliminarMiembro(m1);

        repositorioComunidades.agregar(comunidad1);

        Assertions.assertEquals(0, comunidad1.getMiembros().size());
    }


    @Test
    public void testObtenerMiembroComunidades(){
        RepositorioMiembroComunidad repositorioMiembroComunidad = new RepositorioMiembroComunidad();
        List<MiembroComunidad> miembroComunidades = repositorioMiembroComunidad.obtenerMiembroComunidades(1);
        System.out.println("Cantidad de miembroComunidades: " + miembroComunidades.size());
    }

}
