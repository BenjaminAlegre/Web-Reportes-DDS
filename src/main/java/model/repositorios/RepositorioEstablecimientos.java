package model.repositorios;

import db.EntityManagerHelper;
import model.entities.entidades.Establecimiento;
import model.entities.servicio.Servicio;

public class RepositorioEstablecimientos {

    public Establecimiento buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Establecimiento.class, id);
    }
}
