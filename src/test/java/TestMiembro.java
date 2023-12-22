import model.entities.comunidad.*;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioMiembroComunidad;
import model.repositorios.RepositorioMiembros;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestMiembro {

    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
    RepositorioMiembroComunidad repositorioMiembroComunidad = new RepositorioMiembroComunidad();
    Miembro miembro = new Miembro("Leo", "Messi", "leo_messi@gmail.com", "+541138157280");

    @Test
    public void probarContacto(){
        miembro.setMedioNotificacion(MedioNotificacion.WHATSAPP);
        Assert.assertEquals(miembro.contacto(), "+541138157280");
        miembro.setMedioNotificacion(MedioNotificacion.CORREO_ELECTRONICO);
        Assert.assertEquals(miembro.contacto(),"leo_messi@gmail.com");
    }
    @Test
    public void testAgregarMiembroAComunidadTipo() {

        Miembro m1 = repositorioMiembros.buscarPorId(1);
        Comunidad comunidad1 = repositorioComunidades.buscarPorId(1);
        MiembroComunidad miembroComunidad = new MiembroComunidad(comunidad1, m1, TipoMiembro.OBSERVADOR);
        repositorioMiembroComunidad.agregar(miembroComunidad);

        Comunidad comunidad2 = repositorioComunidades.buscarPorId(2);
        MiembroComunidad miembroComunidad2 = new MiembroComunidad(comunidad2, m1, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad2);
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


}
