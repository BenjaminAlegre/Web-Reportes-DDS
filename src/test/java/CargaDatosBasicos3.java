import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Usuario;
import model.entities.entidades.*;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.Banio;
import model.entities.servicio.Monitoreable;
import model.entities.servicio.Servicio;
import model.entities.servicio.TipoDeBanio;
import model.repositorios.*;
import model.repositorios.incidentes.RepositorioIncidentes;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static model.entities.entidades.TipoOrganizacion.BANCO;

public class CargaDatosBasicos3 {

    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    RepositorioPersonasJuridicas repositorioPersonasJuridicas = new RepositorioPersonasJuridicas();
    RepositorioMiembroComunidad repositorioMiembroComunidad = new RepositorioMiembroComunidad();
    RepositorioServicios repositorioServicios = new RepositorioServicios();
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();



    @Test
    public void cargarMasSupermercados(){
        String[] nombresOrganizaciones = {"Vea", "Diarco", "Jumbo"};
        String[] sucursales ={"Sucursal 01", "Sucursal 02", "Sucursal 03"};

        for (String nombre: nombresOrganizaciones
        ) {
            Organizacion organizacion = new Organizacion();
            organizacion.setNombre(nombre);
            organizacion.setTipo(TipoOrganizacion.SUPERMERCADO);
            this.agregarSucursales(organizacion, sucursales);
            //----- carga banios subtes

            Comunidad baniosSuper = repositorioComunidades.buscarPorId(4);
            for (Sucursal e: organizacion.getSucursales()
            ) {
                List<Monitoreable> banios = e.getMonitoreables().stream().filter(m ->m.esBanio()).collect(Collectors.toList());
                for (Monitoreable m: banios
                ) {
                    m.vincularAComunidad(baniosSuper);
                }

            }

            repositorioEntidades.agregar(organizacion);
        }
    }



    private void agregarSucursales(Organizacion organizacion, String[] sucursales) {
        for (String nombreSucursal : sucursales
        ) {
            Sucursal sucursal = new Sucursal();
            sucursal.setNombre(nombreSucursal);
            organizacion.agregarSucursal(sucursal);
            agregarBanios(sucursal);

        }
    }
    private void agregarBanios(Establecimiento est) {
        for (TipoDeBanio tipo: TipoDeBanio.values()
        ) {
            Banio banio = new Banio(tipo);
            est.agregarMonitoreable(banio);

        }
    }

}
