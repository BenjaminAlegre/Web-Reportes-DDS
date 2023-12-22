package model.repositorios;

import db.EntityManagerHelper;
import model.entities.comunidad.Miembro;
import model.entities.servicio.*;

import java.util.List;

public class RepositorioServicios {
    public void agregar(Servicio servicio) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(servicio);
        EntityManagerHelper.commit();
    }

    public List<Servicio> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Servicio.class.getName())
                .getResultList();
    }

    public Servicio buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Servicio.class, id);
    }
    public List<Monitoreable> buscarServiciosPorIdEstablecimiento(Integer id){
        return EntityManagerHelper.getEntityManager().createQuery("from "+ Monitoreable.class.getName() +" where establecimiento_id="+ id).getResultList();
    }


    public List<Banio> buscarBaños() {
        return EntityManagerHelper.getEntityManager().createQuery("from "+ Monitoreable.class.getName() +" where tipo= banio").getResultList();

    }

    public List<MedioElevacion> buscarMediosElevacion() {
        return EntityManagerHelper.getEntityManager().createQuery("from "+ Monitoreable.class.getName() +" where tipo= medio_elevacion").getResultList();
    }
}
