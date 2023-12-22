package TestEntities;

import model.entities.entidades.Establecimiento;
import model.entities.servicio.Monitoreable;
import model.repositorios.RepositorioEstablecimientos;
import model.repositorios.RepositorioServicios;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestMonitoreables {

    @Test
    public void traerMonitoreablesPorEstablecimiento(){
        RepositorioServicios repo = new RepositorioServicios();
        List<Monitoreable> servicicios = repo.buscarServiciosPorIdEstablecimiento(29);
        for (Monitoreable m: servicicios
             ) {
            System.out.println(m.tipo());
        }
    }
}
