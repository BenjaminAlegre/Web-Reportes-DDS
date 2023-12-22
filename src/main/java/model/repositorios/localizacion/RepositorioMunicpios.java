package model.repositorios.localizacion;

import db.EntityManagerHelper;
import model.entities.localizacion.*;

import model.entities.normalizaciondirecciones.adapters.ServicioGeoDds;
import model.entities.normalizaciondirecciones.adapters.ServicioNormalizacion;
import model.entities.normalizaciondirecciones.entidadesDeNormalizacion.ListadoMunicipios;
import model.entities.normalizaciondirecciones.entidadesDeNormalizacion.ListadoMunicipiosProvincia;

import java.io.IOException;
import java.util.List;

public class RepositorioMunicpios {

    private ServicioNormalizacion api = new ServicioGeoDds();

    public RepositorioMunicpios() throws Exception {
    }

    public void agregar(Municipio municipio) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(municipio);
        EntityManagerHelper.commit();
    }


    public List<Municipio> buscar(Localizacion localizacion) throws IOException {
        if(Provincia.esProvincia(localizacion))
            return this.buscarPorProvincia((Provincia) localizacion);
        else
            return this.buscarPorDepartamento((Departamento) localizacion);
    }

    public List<Municipio> buscarPorProvincia(Provincia provincia) throws IOException {
        List<Municipio> persisitido = this.traerDeBaseDeDatosPorProvincia(provincia);
        if(persisitido.isEmpty()) {
            List<Municipio> listado =  this.trearDesdeApiPorPorProvincia(provincia);
            for (Municipio m: listado
                 ) {
                this.agregar(m);
            }
            return listado;
        }
        else
            return persisitido;
    }
    public List<Municipio> buscarPorDepartamento(Departamento departamento) throws IOException {
        List<Municipio> persisitido = this.traerDeBaseDeDatosPorDepartamento( departamento);
        if(persisitido.isEmpty()) {
            List<Municipio> listado = this.trearDesdeApiPorDepartamento(departamento);
            for (Municipio m: listado
            ) {
                this.agregar(m);
            }
            return listado;
        }
        else
            return persisitido;
    }


    public List<Municipio> traerDeBaseDeDatosPorProvincia(Provincia provincia) {
        return EntityManagerHelper.getEntityManager()
                .createQuery("from" + Municipio.class.getName() + " where provincia_id='" + provincia.id + "'").getResultList();
    }

    public List<Municipio> traerDeBaseDeDatosPorDepartamento(Departamento departamento) {
        return EntityManagerHelper.getEntityManager()
                .createQuery("from" + Municipio.class.getName() + " where departmento_id='" + departamento.id + "'").getResultList();
    }

    private List<Municipio> trearDesdeApiPorPorProvincia(Provincia provincia) throws IOException {
        ListadoMunicipiosProvincia listado = this.api.listadoMunicipiosProvincia(provincia);
        for (Municipio m : listado.municipios
        ) {
            this.agregar(m);
        }
        return  listado.municipios;
    }

    private List<Municipio> trearDesdeApiPorDepartamento(Departamento departamento) throws IOException {
        ListadoMunicipios listado = this.api.listadoMunicipiosDepartamento(departamento);
        for (Municipio m : listado.municipios
        ) {
            m.setDepartamento(listado.nombreDepartamento());
            this.agregar(m);
        }
        return  listado.municipios;
    }

}
