package model.entities.servicio;

import lombok.Getter;
import lombok.Setter;
import DTO.MonitoreableDTO;
import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.entidades.Establecimiento;
import model.entities.notificacion.Incidente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Monitoreable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

//    @OneToMany(mappedBy = "servicioAfectado")
    @Transient
    private List<Incidente> incidentes;

    @Column
    private Boolean funcionamientoHabitual;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "miembros_monitoreables",
            joinColumns = @JoinColumn(name="monitoreable_id"),
            inverseJoinColumns=@JoinColumn(name="miembro_id"))
    private List<Miembro> miembros;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private Establecimiento establecimiento;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private Agrupamiento agrupamiento;

    @ManyToMany(mappedBy = "servicios")
    private List<Comunidad> comunidades = new ArrayList<>();


    public void agregarIncidente(Incidente incidente) {
        incidentes.add(incidente);
    }

    public void eliminarIncidente(Incidente incidente) {
        incidentes.add(incidente);
    }


    public Entidad entidad() {
        return this.establecimiento.entidad();
    }


    public abstract String descripcion();

    public void agregarseAEstablecimiento(Establecimiento establecimiento){
        this.establecimiento = establecimiento;
    }

    public void destruirMonitoreable() {
    }

    public MonitoreableDTO convertirADTO() {
        return new MonitoreableDTO(this);
    }

    public abstract String tipo();

    public void agregarIncidenteAComunidad(Incidente incidente) {
        for(Comunidad c: this.comunidades){
            c.agregarIncidentes(incidente);
        }
    }

    public void vincularAComunidad(Comunidad comunidad) {
        this.comunidades.add(comunidad);
        comunidad.agregarMonitoreable(this);
    }

    public abstract boolean esBanio();

    public abstract boolean esEscalera();
}
