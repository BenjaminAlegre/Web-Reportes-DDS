package model.entities.entidades;


import DTO.EntidadDTO;
import DTO.EstablecimientoDTO;
import lombok.Getter;
import lombok.Setter;
import model.entities.persistencia.EntidadPersistente;
import model.entities.servicio.Monitoreable;
import model.entities.servicio.Servicio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // justificacion: como solo hay 3 atributos distintos creemos que la mejor opcion es in single table
@DiscriminatorColumn(name = "tipoEstablecimiento")
public abstract class Establecimiento extends EntidadPersistente {


    @Column
    private String nombre;

    @OneToOne
    private Direccion ubicacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "establecimiento",cascade = CascadeType.ALL)
    private List<Monitoreable> monitoreables = new ArrayList<>();


    public void agregarMonitoreable(Monitoreable monitoreable){
        monitoreables.add(monitoreable);
        monitoreable.agregarseAEstablecimiento(this);
    }

    public void quitarMonitoreable(Monitoreable monitoreable){
        monitoreables.remove(monitoreable);
        monitoreable.destruirMonitoreable();
    }


    public abstract Entidad entidad();


    public abstract String descripcion();

    public EstablecimientoDTO convertirADTO() {
        return new EstablecimientoDTO(this);
    }
}
