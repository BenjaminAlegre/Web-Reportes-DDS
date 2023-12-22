package model.entities.localizacion;

import javax.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("provincia")
public class Provincia extends Localizacion {



    @OneToMany(fetch = FetchType.LAZY)
    private List<Departamento> departamentos;
    public void traerContenido(){

    }


    public static Boolean esProvincia(Localizacion localizacion) {
        for (Provincias p : Provincias.values()) {
            if (p.label.equals(localizacion.nombre)) {
                return true;
            }
        }
        return false;
    }

}
