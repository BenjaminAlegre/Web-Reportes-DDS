package model.repositorios.localizacion;

import db.EntityManagerHelper;
import model.entities.localizacion.Departamento;

import model.entities.localizacion.Provincia;
import model.entities.normalizaciondirecciones.adapters.ServicioGeoDds;
import model.entities.normalizaciondirecciones.adapters.ServicioNormalizacion;
import model.entities.normalizaciondirecciones.entidadesDeNormalizacion.ListadoDepartamentos;


import java.io.IOException;
import java.util.List;

public class RepositorioDepartamentos {



    private ServicioNormalizacion api = new ServicioGeoDds();

    public RepositorioDepartamentos() throws Exception {
    }


    public void agregar(Departamento departamento) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(departamento);
        EntityManagerHelper.commit();
    }

    public List<Departamento> buscarPorProvincia(Provincia provincia) throws IOException {
        List<Departamento> persisitido = this.traerDeBaseDeDatos(provincia);
        if(persisitido.isEmpty()) {
            List<Departamento> listado = this.trearDesdeApi(provincia);
            for (Departamento d: listado
                 ) {
                this.agregar(d);
            }
            return listado;
        }
        else
            return persisitido;
    }



    public List<Departamento> traerDeBaseDeDatos(Provincia provincia) {
        return EntityManagerHelper.getEntityManager()
                .createQuery("from" + Departamento.class.getName() + " where municipio_id='" + provincia.nombre + "'").getResultList();
    }

    private List<Departamento> trearDesdeApi(Provincia provincia) throws IOException {
        List<Departamento> listado = this.api.listadoDepartamentos(provincia);
        for (Departamento d : listado
        ) {
           // d.setProvincia(provincia.nombre);
            this.agregar(d);
        }
        return  listado;
    }
}
