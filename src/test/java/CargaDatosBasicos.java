import model.entities.comunidad.*;
import model.entities.entidades.*;
import model.entities.localizacion.Provincia;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.*;
import model.repositorios.*;
import model.repositorios.incidentes.RepositorioIncidentes;
import model.repositorios.localizacion.RepositorioProvincias;
import model.repositorios.rankings.RepositorioTramo;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static model.entities.entidades.TipoOrganizacion.BANCO;
import static model.entities.entidades.Transporte.FERROCARRIL;
import static model.entities.entidades.Transporte.SUBTE;

public class CargaDatosBasicos {

    // Despues de la carga de datos hay que correr el test de TestCrearIncidentes, TestMiembro y TestRanking
    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    RepositorioPersonasJuridicas repositorioPersonasJuridicas = new RepositorioPersonasJuridicas();

//-----------------------0
    @Test
    public void traerProvinciasDeAPI() throws Exception {
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        List<Provincia> provincias =repositorioProvincias.buscarTodos();
        for (Provincia p: provincias
        ) {
            System.out.println(p.id);
            System.out.println(p.nombre);
            System.out.println(" ");
        }

        Assert.assertEquals(provincias.size(),24);
    }

    @Test
    public void cargarUsuario(){
        String[][] usuarios = {{"juanrodriguez10@example.com", "1234"},
                            {"maria1234@example.com", "1234"},
                            {"matiasgarcia@example.com", "1234"}};

        Usuario usuario = new Usuario("miembro@miembro.com", "1234", Rol.USUARIO);
        Usuario usuario2 = new Usuario("entidad@entidad.com", "1234", Rol.USUARIO);
        Usuario usuario3 = new Usuario("administrador@admnistrador.com", "1234", Rol.ADMINSTRADOR);
        repositorioUsuarios.agregar(usuario);
        repositorioUsuarios.agregar(usuario2);
        repositorioUsuarios.agregar(usuario3);
    }



//--------------------------1
////----------------------------------------------COMUNIDADES
@Test
public void cargarComunidades() {

    String[] comunidades = {"Baños de Subtes", "Escaleras de subtes", "Ascensores Lineas", "Baños Supermercados", "Comunidad embarazo", "Comunidad Elevadores", "Comunidad Ascensor" , "Comunidad Molinetes", "Comunidad Accesibilidad", "Comunidad Seguridad", "Comunidad Infantes"};
    //Cargar comunidades
    for (String nombre: comunidades
    ) {
        Comunidad comunidad = new Comunidad(nombre);
        repositorioComunidades.agregar(comunidad);
    }
}
    @Test
    public void cargarMiembros(){
        String[][] miembros = {{"Lionel", "Messi", "miembro@miembro.com","+541138157280"},
                {"Rodrigo", "De Paul", "entidad@entidad.com", "+5491138157280"},
                {"Angel", "Di María", "administrador@admnistrador.com", "+5491138157280"},
                {"Dibu", "Martinez", "miembro2@miembro.com", "+5491138157280"}};
        for (String[] dato: miembros
        ) {
            Miembro miembro = new Miembro(dato[0], dato[1], dato[2], dato[3]);
            miembro.setMedioNotificacion(MedioNotificacion.WHATSAPP);
            repositorioMiembros.agregar(miembro);
        }


    }



}
