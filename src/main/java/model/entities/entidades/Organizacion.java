package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter@Setter
@Entity
@DiscriminatorValue("Organizacion")
public class Organizacion extends Entidad{


    @Enumerated(EnumType.STRING)
    private TipoOrganizacion tipo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<Sucursal> sucursales = new ArrayList<>();

    public void agregarSucursal(Sucursal sucursal){
        sucursales.add(sucursal);
        sucursal.setOrganizacion(this);
    }

    public void eliminarSucursal(Sucursal sucursal){
        sucursales.remove(sucursal);
    }


    @Override
    public List<Establecimiento> getEstablecimientos() {
        return  this.sucursales.stream().map(sucursal -> (Establecimiento) sucursal).collect(Collectors.toList());
    }

    @Override
    public boolean esOrganizacion() {
        return true;
    }
}
