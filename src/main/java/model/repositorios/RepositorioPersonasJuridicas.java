package model.repositorios;

import db.EntityManagerHelper;
import model.entities.comunidad.Comunidad;
import model.entities.entidades.Entidad;
import model.entities.entidades.EntidadPrestadora;
import model.entities.entidades.PersonaJuridica;


public class RepositorioPersonasJuridicas {

    public Integer existeCuit(String cuit) {
        PersonaJuridica personaJuridica = null;
        try {
            personaJuridica = (PersonaJuridica) EntityManagerHelper.getEntityManager()
                    .createQuery("from " + PersonaJuridica.class.getName() + " where cuit=" + cuit).getSingleResult();
        }catch(Exception e){
            return null;
        }
        if(personaJuridica != null)
            return personaJuridica.getId();
        else
            return null;
    }

    public PersonaJuridica buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(PersonaJuridica.class, id);
    }


    public void agregar(EntidadPrestadora entidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(entidad);
        EntityManagerHelper.commit();
    }


}
