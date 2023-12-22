package model.repositorios.rankings;

import db.EntityManagerHelper;
import model.entities.servicio.Tramo;

public class RepositorioTramo {
    public void guardarTramo(Tramo tramo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tramo);
        EntityManagerHelper.commit();
    }
}
