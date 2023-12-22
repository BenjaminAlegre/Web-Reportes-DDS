package model.entities.servicio;

import model.entities.comunidad.Miembro;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agrupamiento")
@DiscriminatorValue("agrupamiento")
public class Agrupamiento extends Monitoreable {


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Monitoreable> componentes;

    public Agrupamiento(List<Monitoreable> componentes) {
        this.componentes = new ArrayList<>();
    }

    public Agrupamiento() {
    }

    public void agregarMonitoreable(Monitoreable monitoreable) {
        componentes.add(monitoreable);
    }

    public void eliminarMonitoreable(Monitoreable monitoreable) {
        componentes.remove(monitoreable);
    }

    @Override
    public String descripcion() {
        String cadena = "Servicios:";
        for (Monitoreable m: componentes
             ) {
            cadena.concat(" "+m.descripcion()+",");
        }
        cadena = cadena.substring(0, cadena.length() - 1)+".";
        return cadena;
    }

    @Override
    public String tipo() {
        return "Agrupamiento";
    }

    @Override
    public boolean esBanio() {
        return false;
    }

    @Override
    public boolean esEscalera() {
        return false;
    }
}
