import model.entities.localizacion.Departamento;
import model.entities.localizacion.Municipio;
import model.entities.normalizaciondirecciones.adapters.ServicioGeoDds;
import model.entities.normalizaciondirecciones.entidadesDeNormalizacion.*;
import model.entities.localizacion.Provincia;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TestNormalizacion {

    public ServicioGeoDds servicioAPI = new ServicioGeoDds();

    public TestNormalizacion() throws Exception {
    }

    @Test
    public void obtenerProvincias() throws Exception {


        List<Provincia> provincias = servicioAPI.listadoProvincias();
        for (Provincia p : provincias
        ) {
            System.out.println("Id:"+p.id+" nombre:"+p.nombre);
        }
        System.out.println(provincias.size());
    }

    @Test
    public void normalizarDireccion() throws IOException {
        ListadoPosiblesDirecciones direccionesNormalizadas = servicioAPI.normalizarDireccion("avenida santa fe 1270");
        for (Direccion d: direccionesNormalizadas.direcciones
             ) {
            System.out.println(d.toString());

        }
    }

    @Test
    public void traerDepartamentosBuenosAires() throws IOException {
        Provincia provincia = new Provincia();
        provincia.nombre = "buenos aires";
        ListadoMunicipiosProvincia listado = this.servicioAPI.listadoMunicipiosProvincia(provincia
              );
        for (Municipio d: listado.municipios
             ) {
            System.out.println(d.nombre);
        }
    }

    @Test
    public void traerDepartamentosMisiones() throws IOException {
        Provincia provincia = new Provincia();
        provincia.nombre = "misiones";
        List<Departamento> listado = this.servicioAPI.listadoDepartamentos(provincia
        );
        for (Departamento d: listado
        ) {
            System.out.println(d.nombre);
            System.out.println(d.provincia.nombre);
        }
    }

    @Test
    public void traerProvincias() throws IOException {
        List<Provincia> provincias = this.servicioAPI.listadoProvincias();
        for (Provincia p: provincias
             ) {

            System.out.println(p.nombre);
        }
    }

    @Test
    public void verificarSiEsProvincia(){
        Provincia provincia = new Provincia();
        provincia.nombre = "Buenos Aires";
        Departamento departamento = new Departamento();
        departamento.nombre = "Yapeyu";
//        System.out.println(Provincia.esProvincia(provincia));
//        System.out.println(Provincia.esProvincia(departamento));
        Assert.assertEquals(Provincia.esProvincia(provincia),true);
        Assert.assertEquals(Provincia.esProvincia(departamento),false);
    }
}