package model.repositorios;

import db.EntityManagerHelper;
import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Miembro;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioComunidades {
    public void agregar(Comunidad com) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(com);
        EntityManagerHelper.commit();
    }

    public List<Comunidad> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Comunidad.class.getName())
                .getResultList();
    }

    public Comunidad buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Comunidad.class, id);
    }

    public void actualizar(Comunidad com) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(com);
        EntityManagerHelper.commit();
    }

    public List<Comunidad> obtenerComunidadesDeMiembro(String idMiembro) {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        TypedQuery<Comunidad> query = entityManager.createQuery(
                "SELECT c FROM Comunidad c JOIN c.miembros m WHERE m.id = :idMiembro", Comunidad.class);
        query.setParameter("idMiembro", Integer.parseInt(idMiembro));

        return query.getResultList();
    }

    public Comunidad buscarPorNombre(String nombre) {
        Comunidad comunidad = (Comunidad) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Comunidad.class.getName() + " where nombre="+nombre )
                .getSingleResult();
       if(comunidad != null)
           return comunidad;
       else
           return null;
    }
}
