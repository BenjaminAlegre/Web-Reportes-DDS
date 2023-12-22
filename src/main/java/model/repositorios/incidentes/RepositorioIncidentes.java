package model.repositorios.incidentes;

import db.EntityManagerHelper;
import model.entities.comunidad.Comunidad;
import model.entities.entidades.Entidad;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.repositorios.RepositorioComunidades;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;



public class RepositorioIncidentes {

    public void guardar(Incidente entidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(entidad);
        EntityManagerHelper.commit();
    }

    public List<Incidente> buscarTodos(){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from "+Incidente.class.getName()).getResultList();
    }

    public List<Incidente> buscarPorEntidad(Entidad entidad){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from "+ Incidente.class.getName()+" where entidadAfectada_id="+entidad.getId()).getResultList();
    }
    public List<Incidente> buscarPorNombreEntidad(String nombre){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from "+ Incidente.class.getName()+" where entidadAfectada_id="+nombre).getResultList();
    }

    public List<Incidente> buscarIncidentesPorEntidad(Entidad entidad) {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();

        // JPQL para realizar la consulta
        String jpql = "SELECT i FROM Incidente i WHERE i.entidadAfectada = :entidad";
        TypedQuery<Incidente> query = entityManager.createQuery(jpql, Incidente.class);
        query.setParameter("entidad", entidad);

        return query.getResultList();
    }

    public List<Incidente> obtenerTodos() {
        return EntityManagerHelper.getEntityManager().createQuery("from "+Incidente.class.getName()).getResultList();
    }

    public Incidente buscarPorId(Integer id){
        return EntityManagerHelper.getEntityManager().find(Incidente.class, id);
    }


    public List<Incidente> buscarPorEstado(String estado) {
        EstadoIncidente estadoEnum = EstadoIncidente.valueOf(estado);
        return EntityManagerHelper.getEntityManager()
                .createQuery("from " + Incidente.class.getName() + " where estado = :estado", Incidente.class)
                .setParameter("estado", estadoEnum)
                .getResultList();
    }

    public List<Incidente> buscarPorEstadoYComunidad(String estado, Comunidad comunidad) {
        EstadoIncidente estadoEnum = EstadoIncidente.valueOf(estado);

        EntityManager entityManager = EntityManagerHelper.getEntityManager();


        TypedQuery<Incidente> query = entityManager.createQuery(
                "SELECT i FROM Incidente i JOIN i.comunidades c " +
                        "WHERE i.estado = :estado AND c = :comunidad",
                Incidente.class);

        query.setParameter("estado", estadoEnum);
        query.setParameter("comunidad", comunidad);

        return query.getResultList();
    }



    public void eliminar(Incidente incidente){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(incidente);
        EntityManagerHelper.commit();
    }
    public void actualizar(Incidente incidente){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(incidente);
        EntityManagerHelper.commit();
    }

    public List<Incidente> buscarPorComunidad(List<Comunidad> comunidades) {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();

        EstadoIncidente estadoEnum = EstadoIncidente.ACTIVO;

        String jpql = "SELECT i FROM Incidente i JOIN i.comunidades c WHERE c IN :comunidades AND i.estado = :estado";

        TypedQuery<Incidente> query = entityManager.createQuery(jpql, Incidente.class);
        query.setParameter("comunidades", comunidades);
        query.setParameter("estado", estadoEnum);

        return query.getResultList();
    }
}
