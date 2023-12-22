package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@DiscriminatorValue("LineaDeTransporte")
public class LineaDeTransporte extends Entidad{


    @Enumerated(EnumType.STRING)
    private Transporte tipo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL})
    @JoinTable(name = "estaciones_lineas",
            joinColumns = @JoinColumn(name="estacion_id"),
            inverseJoinColumns=@JoinColumn(name="linea_id"))
    private List<Estacion> estaciones = new ArrayList<>();

    public void agregarEstacion(Estacion estacion){
        this.estaciones.add(estacion);
        estacion.agregarLinea(this);
    }

    @Override
    public List<Establecimiento> getEstablecimientos() {
        return this.estaciones.stream().map(e -> (Establecimiento) e).collect(Collectors.toList());
    }

    @Override
    public boolean esOrganizacion() {
        return false;
    }
}
