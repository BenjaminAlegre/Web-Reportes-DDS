package model.repositorios;

import db.EntityManagerHelper;
import model.entities.comunidad.MiembroComunidad;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositorioMiembroComunidad {

    public void agregar(MiembroComunidad miembroComunidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(miembroComunidad);
        EntityManagerHelper.commit();
    }

    public MiembroComunidad buscarPorId(Integer id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        MiembroComunidad miembroComunidad = em.find(MiembroComunidad.class, id);
        em.close();
        return miembroComunidad;
    }

    public MiembroComunidad buscarPorMiembroYComunidad(Integer idComunidad, Integer idMiembro) {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();

        try {
            // Realizar la consulta utilizando JPQL (Java Persistence Query Language)
            String jpql = "SELECT mc FROM MiembroComunidad mc " +
                    "WHERE mc.miembro.id = :idMiembro " +
                    "AND mc.comunidad.id = :idComunidad";

            MiembroComunidad miembroComunidad = entityManager
                    .createQuery(jpql, MiembroComunidad.class)
                    .setParameter("idMiembro", idMiembro)
                    .setParameter("idComunidad", idComunidad)
                    .getSingleResult();

            return miembroComunidad;
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(MiembroComunidad miembroComunidad) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        em.getTransaction().begin();
        em.remove(miembroComunidad);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizar(MiembroComunidad miembroComunidad) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        em.getTransaction().begin();
        em.merge(miembroComunidad);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminarPorId(Integer id) {
        MiembroComunidad miembroComunidad = this.buscarPorId(id);
        this.eliminar(miembroComunidad);
    }

    public List<MiembroComunidad> buscarTodos() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<MiembroComunidad> miembroComunidades = em.createQuery("from MiembroComunidad").getResultList();
        em.close();
        return miembroComunidades;
    }

//    public List<MiembroComunidad> obtenerMiembroComunidades(String idMiembro) {
//        EntityManager em = EntityManagerHelper.getEntityManager();
//        List<MiembroComunidad> miembroComunidades = em.createQuery("from MiembroComunidad where miembro_id = " + idMiembro).getResultList();
//        em.close();
//        return miembroComunidades;
//    }

    public List<MiembroComunidad> obtenerMiembroComunidades(Integer idMiembro) {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();

        try {
            // Realizar la consulta utilizando JPQL (Java Persistence Query Language)
            String jpql = "SELECT mc FROM MiembroComunidad mc " +
                    "WHERE mc.miembro.id = :idMiembro";

            List<MiembroComunidad> miembroComunidades = entityManager
                    .createQuery(jpql, MiembroComunidad.class)
                    .setParameter("idMiembro", idMiembro)
                    .getResultList();

            return miembroComunidades;
        } catch (Exception e) {
            System.out.println("ERRROOOOOOOOOOOOOOOOOOOOOOOR");
            throw new RuntimeException(e);
}
        finally {
            entityManager.close();
        }
    }

}
