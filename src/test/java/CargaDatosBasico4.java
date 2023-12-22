import model.entities.comunidad.Usuario;
import model.entities.entidades.Entidad;
import model.entities.entidades.EntidadPrestadora;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.Servicio;
import model.repositorios.*;
import model.repositorios.incidentes.RepositorioIncidentes;
import org.junit.Test;

import java.time.LocalDateTime;

public class CargaDatosBasico4 {
    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    RepositorioPersonasJuridicas repositorioPersonasJuridicas = new RepositorioPersonasJuridicas();
    RepositorioMiembroComunidad repositorioMiembroComunidad = new RepositorioMiembroComunidad();
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
    RepositorioServicios repositorioServicios = new RepositorioServicios();

    //---------------------------------------------6

    @Test
    public void cargarIncidentesBaniosSupermercados(){
        for (int i = 1; i<= 6; i++){
            Incidente incidente = new Incidente();
            incidente.setReportador(" Sistema ");
            incidente.setObservaciones("Observaciones de incidente baños numero: " + i);
            incidente.setEstado(EstadoIncidente.ACTIVO);
            incidente.setHorarioApertura(LocalDateTime.now());
            // incidente.setHorarioCierre(LocalDateTime.now().plusDays(5));

            Servicio servicio = repositorioServicios.buscarPorId(i*3);


            incidente.setServicioAfectado(servicio);
            incidente.setEntidadAfectada( servicio.entidad());
            incidente.agregarIncidenteAComunidad();

            repositorioIncidentes.guardar(incidente);
            System.out.println("Incidente " + i*3 + " guardado");
        }

    }
    @Test
    public void cargarIncidentesElevaciones(){

        for (int i = 0; i<= 6; i++){
            Incidente incidente = new Incidente();
            incidente.setReportador(" Sistema ");
            incidente.setObservaciones("Observaciones del incidente elevacion numero: " + i);
            incidente.setEstado(EstadoIncidente.ACTIVO);
            incidente.setHorarioApertura(LocalDateTime.now());
            // incidente.setHorarioCierre(LocalDateTime.now().plusDays(5));

            Servicio servicio = repositorioServicios.buscarPorId(113+i*8);

            incidente.setServicioAfectado(servicio);
            incidente.setEntidadAfectada( servicio.entidad());
            incidente.agregarIncidenteAComunidad();

            repositorioIncidentes.guardar(incidente);
            System.out.println("Incidente " + 113+i*8 + " guardado");
        }

    }

    //-------------------------------------7
    @Test
    public void crearEntidadPrestadora(){

        Entidad coto = repositorioEntidades.buscarPorId(10);
        Entidad bbva =repositorioEntidades.buscarPorId(7);
        Entidad dia = repositorioEntidades.buscarPorId(11);

        EntidadPrestadora entidadPrestadora = new EntidadPrestadora();
        entidadPrestadora.setNombre("Picaros SRL");
        entidadPrestadora.setTelefono(12345678);
        entidadPrestadora.setCuit("123456789");

        Usuario usuarioEntidad = repositorioUsuarios.buscarPorId(2);
        usuarioEntidad.setPerfil(entidadPrestadora);
        entidadPrestadora.setUsuario(usuarioEntidad);

        dia.setPersonaJuridica(entidadPrestadora);
        bbva.setPersonaJuridica(entidadPrestadora);
        coto.setPersonaJuridica(entidadPrestadora);

        repositorioPersonasJuridicas.agregar(entidadPrestadora);
        repositorioEntidades.actualizar(dia);
        repositorioEntidades.actualizar(bbva);
        repositorioEntidades.actualizar(coto);
    }
}
