package model.entities.comunidad;

import lombok.Getter;
import lombok.Setter;

import model.entities.entidades.Entidad;
import model.entities.notificacion.*;
import model.entities.persistencia.EntidadPersistente;
import model.entities.servicio.Monitoreable;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Comunidad extends EntidadPersistente implements Observable {

    @Column
    private String nombre;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "miembros_comunidad",
            joinColumns = @JoinColumn(name="comunidad_id"),
            inverseJoinColumns=@JoinColumn(name="miembro_id"))
    private List<Miembro> miembros;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "administrador_comunidad",
            joinColumns = @JoinColumn(name="comunidad_id"),
            inverseJoinColumns=@JoinColumn(name="miembro_id"))
    private List<Miembro> administradores;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "incidentes_comunidad",
            joinColumns = @JoinColumn(name="comunidad_id"),
            inverseJoinColumns=@JoinColumn(name="incidente_id"))
    private List<Incidente> incidentes;

    @Transient
    public List<Observador> observadores;

    @OneToMany(mappedBy = "comunidad")
    private List<MiembroComunidad> miembrosComunidad = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "servicios_comunidad",
            joinColumns = @JoinColumn(name="comunidad_id"),
            inverseJoinColumns=@JoinColumn(name="servicio_id"))
    private List<Monitoreable> servicios = new ArrayList<>();

    public Comunidad(List<Miembro> miembros, List<Miembro> administradores, List<Incidente> incidentes) {
        this.miembros = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }

    public Comunidad() {
    }

    public void agregarMonitoreable(Monitoreable servicio){
        this.servicios.add(servicio);

    }

    public void agregarMonitoreable(Monitoreable ... servicios){
        this.servicios.addAll(Arrays.asList(servicios));
        for (Monitoreable s: servicios
             ) {
            s.vincularAComunidad(this);
        }
}

    public void agregarMiembro(Miembro miembro) {
        miembros.add(miembro);
    }
    public void agregarAdministrador(Miembro administrador) {
        administradores.add(administrador);
    }
    public void eliminarMiembro(Miembro miembro) {
        miembros.remove(miembro);
    }
//    public void eliminarAdministrador(Miembro administrador) {
//        administradores.remove(administrador);
//    }
    public void agregarIncidentes(List<Incidente> incidentes){
        this.incidentes.addAll(incidentes);
    }

    public void agregarIncidentes(Incidente incidentes){
        this.incidentes.add(incidentes);

    }

    public List<Incidente> consultarIncidentesPorEstado(EstadoIncidente estado){
        return  this.incidentes.stream().filter(incidente -> incidente.getEstado().equals(estado)).collect(Collectors.toList());
    }

    public void agregarObservador(Observador observador){
        this.observadores.add(observador);
    }
    public void eliminarObservador(Observador observador){
        this.observadores.remove(observador);
    }

    @Override
    public void notificar(String mensaje){

//        this.observadores.forEach(observador -> observador.serNotificadoPor(this, mensja));
    }


    public List<Incidente> incidentesPorHorario(Miembro m) {
        return this.incidentes.stream().filter(i -> i.reportableAMiembro(m)).collect(Collectors.toList());
    }

    public Comunidad(String nombre) {
        this.nombre = nombre;
    }
}
