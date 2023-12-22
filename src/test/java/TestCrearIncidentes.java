import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.entidades.Organizacion;
import model.entities.entidades.TipoOrganizacion;
import model.entities.localizacion.Localizacion;
import model.entities.localizacion.Provincia;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.Banio;
import model.entities.servicio.Servicio;
import model.entities.servicio.TipoDeBanio;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioEntidades;
import model.repositorios.RepositorioServicios;
import model.repositorios.incidentes.RepositorioIncidentes;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestCrearIncidentes {

    Miembro miembro = new Miembro("Leo", "Messi", "leo_messi@gmail.com", "+541138157280");


    Banio banioCoto = new Banio(TipoDeBanio.HOMBRES);
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();

    RepositorioServicios repositorioServicios = new RepositorioServicios();
    RepositorioComunidades repositorioComunidades = new RepositorioComunidades();

    @Test
    public void cargarIncidenteAComunidad(){
//        List<Comunidad> comunidades = repositorioComunidades.buscarTodos();
//        List<Incidente> incidentes = repositorioIncidentes.buscarTodos();
//        for(Comunidad comunidad : comunidades){
//            for(Incidente incidente : incidentes){
//                comunidad.agregarIncidentes(incidente);
//            }
//
//            repositorioComunidades.actualizar(comunidad);
//            System.out.println("Incidente  cargado a la comunidad ");
//        }

        Comunidad comunidad = repositorioComunidades.buscarPorId(1);
        Incidente incidente = repositorioIncidentes.buscarPorId(1);
        Incidente incidente2 = repositorioIncidentes.buscarPorId(2);
        Incidente incidente3 = repositorioIncidentes.buscarPorId(3);
        Incidente incidente4 = repositorioIncidentes.buscarPorId(4);
        Incidente incidente5 = repositorioIncidentes.buscarPorId(5);
        List<Incidente> incidentes = new ArrayList<>();
        incidentes.add(incidente);
        incidentes.add(incidente2);
        incidentes.add(incidente3);
        incidentes.add(incidente4);
        incidentes.add(incidente5);
        comunidad.agregarIncidentes(incidentes);
        repositorioComunidades.actualizar(comunidad);

        Comunidad comunidad2 = repositorioComunidades.buscarPorId(2);
        Incidente incidente6 = repositorioIncidentes.buscarPorId(6);
        Incidente incidente7 = repositorioIncidentes.buscarPorId(7);
        Incidente incidente8 = repositorioIncidentes.buscarPorId(8);
        Incidente incidente9 = repositorioIncidentes.buscarPorId(9);
        Incidente incidente10 = repositorioIncidentes.buscarPorId(10);
        List<Incidente> incidentes2 = new ArrayList<>();
        incidentes2.add(incidente6);
        incidentes2.add(incidente7);
        incidentes2.add(incidente8);
        incidentes2.add(incidente9);
        incidentes2.add(incidente10);
        comunidad2.agregarIncidentes(incidentes2);
        repositorioComunidades.actualizar(comunidad2);

        Comunidad comunidad3 = repositorioComunidades.buscarPorId(3);
        Incidente incidente11 = repositorioIncidentes.buscarPorId(11);
        Incidente incidente12 = repositorioIncidentes.buscarPorId(12);
        Incidente incidente13 = repositorioIncidentes.buscarPorId(13);
        Incidente incidente14 = repositorioIncidentes.buscarPorId(14);
        Incidente incidente15 = repositorioIncidentes.buscarPorId(15);
        List<Incidente> incidentes3 = new ArrayList<>();
        incidentes3.add(incidente11);
        incidentes3.add(incidente12);
        incidentes3.add(incidente13);
        incidentes3.add(incidente14);
        incidentes3.add(incidente15);
        comunidad3.agregarIncidentes(incidentes3);
        repositorioComunidades.actualizar(comunidad3);

        Comunidad comunidad4 = repositorioComunidades.buscarPorId(4);
        Incidente incidente16 = repositorioIncidentes.buscarPorId(16);
        List<Incidente> incidentes4 = new ArrayList<>();
        incidentes4.add(incidente16);
        comunidad4.agregarIncidentes(incidentes4);
        repositorioComunidades.actualizar(comunidad4);
    }

    @Test
    public void crearCoto(){
        Organizacion coto = new Organizacion();
        coto.setNombre("COTO");
        coto.setTipo(TipoOrganizacion.SUPERMERCADO);
        //coto.setLocalizacion(new Provincia());
    }

    @Test
    public void traerIncidentesPorEntidad(){
        Entidad entidad = repositorioEntidades.buscarPorId(2);
        List<Incidente> incidentesTraido = repositorioIncidentes.buscarIncidentesPorEntidad(entidad);
        Assert.assertEquals(incidentesTraido.get(0).getEstado(), EstadoIncidente.ACTIVO);
        Assert.assertEquals(incidentesTraido.size(), 1);
    }




//    @Test
//    public void traerIncidentesPorEstadoYComunidad(){
//       // List<Incidente> incidentesTraido = repositorioIncidentes.buscarPorEstadoYComunidad("ACTIVO", 4);
//
//        Assert.assertEquals(incidentesTraido.get(0).getEstado(), EstadoIncidente.ACTIVO);
////        Assert.assertEquals(incidentesTraido.get(0).getEstado(), EstadoIncidente.CERRADO);
//        System.out.println(incidentesTraido.get(0).getComunidades().get(0).getNombre());
////        System.out.println(incidentesTraido.get(0).getComunidades().get(0).getIncidentes().get(0).getEstado());
//    }


}
