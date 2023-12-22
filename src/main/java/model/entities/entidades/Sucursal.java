package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DiscriminatorValue("SUCURSAL")
public class Sucursal extends Establecimiento {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Organizacion organizacion;

    @Override
    public Entidad entidad() {
        return this.organizacion;
    }

    @Override
    public String descripcion() {
        return "Sucursal "+ this.getNombre()+" de "+ this.organizacion.getNombre()+" ubicada en "+ this.getUbicacion().toString();
    }


}
