package model.repositorios;

import db.EntityManagerHelper;
import model.entities.entidades.Entidad;
import model.entities.entidades.Organizacion;
import model.entities.entidades.TipoOrganizacion;

import java.util.List;

public class RepositorioEntidades {

    public void agregar(Entidad entidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(entidad);
        EntityManagerHelper.commit();
    }

    public List<Entidad> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Entidad.class.getName())
                .getResultList();
    }

    public Entidad buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Entidad.class, id);
    }

    public void actualizar(Entidad entidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(entidad);
        EntityManagerHelper.commit();
    }


    public List<Entidad> buscarPorTipo(String tipo) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Entidad.class.getName() +  " WHERE tipo='" + tipo + "'" )
                .getResultList();
    }

    public List<Organizacion> buscarPorTipoorg(String tipo) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Entidad.class.getName() + " where tipo=" + TipoOrganizacion.BANCO)
                .getResultList();
    }

    public String obtenerTipoDeEntidad(String entidad) {
        return null;
    }
}
