package model.entities.localizacion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter
@Entity
@DiscriminatorValue("municipio")
public class Municipio extends Localizacion{

    @Column
    public String departamento;

    @Column
    public String provincia;
}
