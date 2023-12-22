import model.entities.comunidad.*;
import model.entities.entidades.*;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.*;
import model.repositorios.*;
import model.repositorios.incidentes.RepositorioIncidentes;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static model.entities.entidades.TipoOrganizacion.BANCO;
import static model.entities.entidades.Transporte.FERROCARRIL;
import static model.entities.entidades.Transporte.SUBTE;

public class CargaDatosBasicos2 {
    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    RepositorioPersonasJuridicas repositorioPersonasJuridicas = new RepositorioPersonasJuridicas();
    RepositorioMiembroComunidad repositorioMiembroComunidad = new RepositorioMiembroComunidad();
    RepositorioServicios repositorioServicios = new RepositorioServicios();
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();

    //---------------------------------------------------------2
    @Test
    public void cargarLineas() {
        String[] nombresLineasSubte = {"Linea A", "Linea B", "Linea H"};
        String[] nombresFerrocarriles = {"Sarmiento", "Roca", "Belgrano Norte"};
        String[][] estacionesSubte = {{"Plaza Miserere", "Loria", "Pasco"},{"Medrano", "Carlos Gardel", "Pueyrredon"},{"Corrientes", "Cordoba", "Once"}};
        String[][] estacionesTrenes ={{"Once", "Caballito", "Flores"}, {"Gerli", "Lanus", "Remedios de Escalada"}, {"Retiro", "Saldias", "R. S. Ortiz"}};
        for (String subte: nombresLineasSubte
        ) {
            int contador = 0;
            LineaDeTransporte linea = new LineaDeTransporte();
            linea.setNombre(subte);
            linea.setTipo(SUBTE);
            this.agregarEstaciones(linea,estacionesSubte[contador]);
            Comunidad bañoSubtes = repositorioComunidades.buscarPorId(1);

            //----- carga banios subtes
            for (Estacion e:linea.getEstaciones()
            ) {
                List<Monitoreable> banios = e.getMonitoreables().stream().filter(m ->m.esBanio()).collect(Collectors.toList());
                for (Monitoreable m: banios
                ) {
                    m.vincularAComunidad(bañoSubtes);
                }

            }

            //--------------carga comundad de elevacion subtes
            Comunidad escalerasSubtes = repositorioComunidades.buscarPorId(2);
            for (Estacion e:linea.getEstaciones()
            ) {
                List<Monitoreable> escaleras = e.getMonitoreables().stream().filter(m -> m.esEscalera()).collect(Collectors.toList());
                for (Monitoreable m: escaleras
                ) {
                    m.vincularAComunidad(escalerasSubtes);
                }

            }

            repositorioEntidades.agregar(linea);
            contador ++;
        }

        for (String tren: nombresFerrocarriles
        ) {
            int contador = 0;
            LineaDeTransporte linea = new LineaDeTransporte();
            linea.setNombre(tren);
            linea.setTipo(FERROCARRIL);
            this.agregarEstaciones(linea, estacionesTrenes[contador]);
            //--------------------------------carga comunidad asensores de linea
            Comunidad ascensoresLineas = repositorioComunidades.buscarPorId(3);
            for (Estacion e:linea.getEstaciones()
            ) {
                List<Monitoreable> escaleras = e.getMonitoreables().stream().filter(m -> !m.esEscalera() && !m.esBanio()).collect(Collectors.toList());
                for (Monitoreable m: escaleras
                ) {
                    m.vincularAComunidad(ascensoresLineas);
                }

            }
            repositorioEntidades.agregar(linea);
            contador ++;
        }

    }

    ////----------------------------------------------3
    @Test
    public void testAgregarMiembroAComunidadTipo() {

        Miembro m1 = repositorioMiembros.buscarPorId(1);
        Comunidad comunidad1 = repositorioComunidades.buscarPorId(1);
        MiembroComunidad miembroComunidad = new MiembroComunidad(comunidad1, m1, TipoMiembro.OBSERVADOR);
        repositorioMiembroComunidad.agregar(miembroComunidad);
        Comunidad comunidad2 = repositorioComunidades.buscarPorId(2);
        MiembroComunidad miembroComunidad2 = new MiembroComunidad(comunidad2, m1, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad2);
        Comunidad comunidad12 = repositorioComunidades.buscarPorId(4);
        MiembroComunidad miembroComunidad12 = new MiembroComunidad(comunidad12, m1, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad12);
        Comunidad comunidad9 = repositorioComunidades.buscarPorId(9);
        MiembroComunidad miembroComunidad9 = new MiembroComunidad(comunidad9, m1, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad9);
        Comunidad comunidad10 = repositorioComunidades.buscarPorId(10);
        MiembroComunidad miembroComunidad10 = new MiembroComunidad(comunidad10, m1, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad10);
        Comunidad comunidad11 = repositorioComunidades.buscarPorId(11);
        MiembroComunidad miembroComunidad11 = new MiembroComunidad(comunidad11, m1, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad11);

        Miembro m3 = repositorioMiembros.buscarPorId(2);
        Comunidad comunidad3 = repositorioComunidades.buscarPorId(3);
        MiembroComunidad miembroComunidad3 = new MiembroComunidad(comunidad3, m3, TipoMiembro.OBSERVADOR);
        repositorioMiembroComunidad.agregar(miembroComunidad3);

        Miembro m4 = repositorioMiembros.buscarPorId(3);
        Comunidad comunidad4 = repositorioComunidades.buscarPorId(4);
        MiembroComunidad miembroComunidad4 = new MiembroComunidad(comunidad4, m4, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad4);

        Miembro m5 = repositorioMiembros.buscarPorId(4);
        Comunidad comunidad5 = repositorioComunidades.buscarPorId(5);
        Comunidad comunidad6 = repositorioComunidades.buscarPorId(6);
        Comunidad comunidad7= repositorioComunidades.buscarPorId(7);
        Comunidad comunidad8 = repositorioComunidades.buscarPorId(8);
        MiembroComunidad miembroComunidad5 = new MiembroComunidad(comunidad5, m5, TipoMiembro.OBSERVADOR);
        MiembroComunidad miembroComunidad6 = new MiembroComunidad(comunidad6, m5, TipoMiembro.AFECTADO);
        MiembroComunidad miembroComunidad7 = new MiembroComunidad(comunidad7, m5, TipoMiembro.AFECTADO);
        MiembroComunidad miembroComunidad8 = new MiembroComunidad(comunidad8, m5, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad5);
        repositorioMiembroComunidad.agregar(miembroComunidad6);
        repositorioMiembroComunidad.agregar(miembroComunidad7);
        repositorioMiembroComunidad.agregar(miembroComunidad8);

    }

    @Test
    public void asociarMiembroAUsuario(){
        Miembro m1 = repositorioMiembros.buscarPorId(1);
        Usuario usuario = repositorioUsuarios.buscarPorId(1);
        m1.setUsuario(usuario);
        repositorioUsuarios.actualizar(usuario);

        Miembro m2 = repositorioMiembros.buscarPorId(2);
        Usuario usuario2 = repositorioUsuarios.buscarPorId(2);
        m2.setUsuario(usuario2);
        repositorioUsuarios.actualizar(usuario2);

        Miembro m3 = repositorioMiembros.buscarPorId(3);
        Usuario usuario3 = repositorioUsuarios.buscarPorId(3);
        m3.setUsuario(usuario3);
        repositorioUsuarios.actualizar(usuario3);
    }

    private void agregarEstaciones(LineaDeTransporte linea, String[] estacionesSubte) {
        for (String estacion: estacionesSubte
        ) {
            Estacion est = new Estacion();
            est.setNombre(estacion);
            linea.agregarEstacion(est);
            agregarBanios(est);

            agregarMediosDeElevacion(est);
        }
    }



    private void agregarMediosDeElevacion(Estacion est) {


        for (TipoDeElevacion tipo: TipoDeElevacion.values()
        ) {
            MedioElevacion medioElevacion = new MedioElevacion(tipo, Tramo.tramo(PuntosTramo.ANDEN, PuntosTramo.ACCESO_A_TRANSPORTE));
            MedioElevacion medioElevacion2 = new MedioElevacion(tipo, Tramo.tramo(PuntosTramo.ANDEN, PuntosTramo.ACCESO_A_TRANSPORTE));
            est.agregarMonitoreable(medioElevacion);
            est.agregarMonitoreable(medioElevacion2);
        }
    }



    //--------------------------------------------SERVICIOS
//-----------------------------------------6
    @Test
    public void cargarBancos() {
        String[] nombresBancos = {"BBVA", "ICBC", "Santander"};
        String[] sucursales = {"Sucursal A", "Sucursal B", "Sucursal C"};

        for (String banco: nombresBancos
        ) {
            Organizacion organizacion = new Organizacion();
            organizacion.setNombre(banco);
            organizacion.setTipo(BANCO);
            this.agregarSucursales(organizacion, sucursales);

            repositorioEntidades.agregar(organizacion);
        }
    }
    @Test
    public void cargarSupermercados(){
        String[] nombresOrganizaciones = {"Coto", "Dia", "Carretfour"};
        String[] sucursales ={"Sucursal 01", "Sucursal 02", "Sucursal 03"};

        for (String nombre: nombresOrganizaciones
        ) {
            Organizacion organizacion = new Organizacion();
            organizacion.setNombre(nombre);
            organizacion.setTipo(TipoOrganizacion.SUPERMERCADO);
            this.agregarSucursales(organizacion, sucursales);
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
