package model.entities.servicio;


import javax.persistence.*;

@Entity
@DiscriminatorValue("servicio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_servicio")
public abstract class Servicio extends Monitoreable {


}