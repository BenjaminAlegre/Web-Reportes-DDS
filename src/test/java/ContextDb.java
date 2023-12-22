import model.entities.comunidad.Comunidad;
import model.entities.comunidad.MedioNotificacion;
import model.entities.comunidad.Miembro;
import model.entities.comunidad.TipoMiembro;
import model.entities.entidades.*;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.*;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioEntidades;
import model.repositorios.RepositorioMiembros;
import model.repositorios.RepositorioServicios;
import model.repositorios.incidentes.RepositorioIncidentes;
import model.repositorios.rankings.RepositorioTramo;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static model.entities.entidades.TipoOrganizacion.BANCO;
import static model.entities.entidades.Transporte.SUBTE;
import static org.junit.Assert.assertNotNull;

public class ContextDb  extends AbstractPersistenceTest implements WithGlobalEntityManager {

        @Test
        public void contextUp(){assertNotNull(entityManager());}

        @Test
        public void contextUpWithTransaction() throws Exception {
            withTransaction(() -> {});
        }

}
