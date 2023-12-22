package model.entities.entidades;

import model.entities.servicio.Monitoreable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ESTACION")
public class Estacion extends Establecimiento {

    @ManyToMany(mappedBy = "estaciones")
    private List<LineaDeTransporte> lineas = new ArrayList<>();

    @Override
    public Entidad entidad() {
        return this.lineas.get(0);
    }

    @Override
    public String descripcion() {
        if(lineas.size() > 1){
            String cadena = "";
            for (LineaDeTransporte l: lineas
                 ) {
                cadena.concat(l.getNombre()+", ");
            }
            return "Estación "+ this.getNombre()+" de las lineas "+ this.lineas.get(0).getNombre()+" ubicada en "+ this.getUbicacion().toString();
        }else
        return "Estación "+ this.getNombre()+" de linea"+ this.lineas.get(0).getNombre()+" ubicada en "+ this.getUbicacion().toString();
    }


    public void agregarLinea(LineaDeTransporte lineaDeTransporte) {
        this.lineas.add(lineaDeTransporte);
    }

    public void agregarMonitoreable(Monitoreable servicio){
        super.agregarMonitoreable(servicio);
    }

}
