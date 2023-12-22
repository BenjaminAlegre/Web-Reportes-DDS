package model.entities.servicio;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
@DiscriminatorValue("banio")
public class Banio extends Servicio {

    @Enumerated( EnumType.STRING )
    private TipoDeBanio tipo;


    @Override
    public String descripcion() {
        return "Baño tipo: "+ this.tipo.toString()+" ubicado en "+ super.getEstablecimiento().descripcion();
    }

    @Override
    public String tipo() {
        return this.getClass().getSimpleName()+" "+ tipo.label;
    }

    @Override
    public boolean esBanio() {
        return true;
    }

    @Override
    public boolean esEscalera() {
        return false;
    }

    public Banio(TipoDeBanio tipo) {
        this.tipo = tipo;
    }

    public Banio() {
    }


}