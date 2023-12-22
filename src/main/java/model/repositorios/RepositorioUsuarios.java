package model.repositorios;

import db.EntityManagerHelper;
import model.entities.comunidad.Usuario;


import java.util.List;

public class RepositorioUsuarios {

    public void agregar(Usuario usuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.commit();
    }
    public List<Usuario> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Usuario.class.getName())
                .getResultList();
    }

    public Usuario buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Usuario.class, id);
    }

    public void actualizar(Usuario usuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(usuario);
        EntityManagerHelper.commit();
    }


}
