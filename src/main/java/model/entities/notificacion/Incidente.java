package model.entities.notificacion;



import lombok.Getter;
import lombok.Setter;

import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.persistencia.EntidadPersistente;
import model.entities.servicio.Monitoreable;
import model.repositorios.incidentes.RepositorioIncidentes;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Getter
@Setter
@Entity
public class Incidente  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idMonitoreable")
    private Monitoreable servicioAfectado;

    @Column
    private String observaciones;

    @Enumerated(EnumType.STRING)
    private EstadoIncidente estado = EstadoIncidente.ACTIVO;

    @Column
    private LocalDateTime horarioApertura = LocalDateTime.now();
    @Column
    private LocalDateTime horarioCierre;

//    @ManyToOne
//    @JoinColumn(name = "id")
    private String reportador;//TODO Desnormalizar?

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private Entidad entidadAfectada;

    @ManyToMany(mappedBy = "incidentes")
    private List<Comunidad> comunidades;



    @Transient
    RepositorioIncidentes repoIncidentes = new RepositorioIncidentes();

    public void guardate(){
        this.repoIncidentes.guardar(this);
    }

    //Repostador string? ,
    public Incidente(String nombre, Monitoreable servicioAfectado, String observaciones) {
        this.reportador = nombre;
        this.servicioAfectado = servicioAfectado;
        this.cambiarEstadoServicioAfectado();
        this.observaciones = observaciones;
        this.entidadAfectada = servicioAfectado.entidad();
    }

    private void cambiarEstadoServicioAfectado() {
        this.servicioAfectado.setFuncionamientoHabitual(false);
    }

    public boolean entraEnCalculoSemanal(LocalDate fecha) {
        return this.estaActivo() & this.estaEnFecha(fecha);
    }

    private boolean estaEnFecha(LocalDate fecha) {
        return this.getHorarioApertura().toLocalDate().isAfter(fecha);
    }


    public Double tiempoDeCierre(){
//        Period periodo = Period.between(horarioCierre, horarioApertura);
//        double diasDiferencia = periodo.getDays() + periodo.getMonths() * 30.44 + periodo.getYears() * 365.25;
//        return diasDiferencia;
        Duration duracion = Duration.between(horarioApertura, horarioCierre);
        return (double) duracion.toHours() / 24; // Devuelve la diferencia en días

    }

    public Incidente() {
    }

//    public Incidente(Monitoreable servicioAfectado, String observaciones, EstadoIncidente estado, LocalDate horarioApertura, LocalDate horarioCierre, String idReportador, Entidad entidadAfectada) {
//        this.servicioAfectado = servicioAfectado;
//        this.observaciones = observaciones;
//        this.estado = estado;
//        this.horarioApertura = horarioApertura;
//        this.horarioCierre = horarioCierre;
//     this.reportador = idReportador;
//        this.entidadAfectada = entidadAfectada;
//    }

    public String descripcionServicioAfectado(){
        return servicioAfectado.descripcion();
    }

    public Boolean reportableAMiembro(Miembro m) { //TODO misma logica que sie netra en calculo semananal pero no se entide lo de las 24hs desde su apertura, creoq ue es asi lo que pide
        return this.estaActivo() & this.estaEnFecha(m.getUltimaNotificacion()) & this.horarioApertura.toLocalDate().isBefore(m.getUltimaNotificacion().plusDays(1));
    }

    private boolean estaActivo() {
        return estado.equals(EstadoIncidente.ACTIVO);
    }


    public boolean resuelto() {
        return this.horarioCierre != null;
    }

    public void agregarIncidenteAComunidad() {
        this.servicioAfectado.agregarIncidenteAComunidad(this);
    }
}
