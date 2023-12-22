package model.entities;


import model.entities.utils.BDUtils;
import javax.persistence.EntityManager;

public class Demo {

    public static void main(String[] args) {

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);



        BDUtils.commit(em);
    }

}
