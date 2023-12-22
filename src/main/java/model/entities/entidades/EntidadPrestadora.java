package model.entities.entidades;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.Getter;
import lombok.Setter;
import model.entities.comunidad.Miembro;

import model.entities.notificacion.*;

import model.entities.notificacion.Incidente;
import model.entities.notificacion.Observable;
import model.entities.notificacion.Suscriber;
import model.entities.servicio.Monitoreable;
import model.repositorios.incidentes.RepositorioIncidentes;


import javax.mail.MessagingException;
import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@DiscriminatorValue("entidadPresadora")

public class EntidadPrestadora extends PersonaJuridica implements Observable, Reportador {




    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "suscripciones",
            joinColumns = @JoinColumn(name="entidad_id"),
            inverseJoinColumns=@JoinColumn(name="suscriptor_id"))
    private List<Miembro> suscriptores = new ArrayList<>();


    @Transient
    Incidente incidenteANotificar;

    public EntidadPrestadora() {

    }

    public void agregarSuscriptor(Miembro miembro){
        suscriptores.add(miembro);
    }


    @Override
    public void generarIncidente(Monitoreable servicioAfectado, String observaciones) {
        this.incidenteANotificar =  new Incidente(this.getNombre(), servicioAfectado, observaciones);
        this.incidenteANotificar.guardate();
        String mensaje = this.generarMensajeIncidente(incidenteANotificar);
        this.notificar(mensaje);
    }


    private String generarMensajeIncidente(Incidente incidenteANotificar) {
        return "La Entidad Prestadora " +this.getNombre()+ " ha reportado un incidente en el servicio "+ incidenteANotificar.descripcionServicioAfectado();
    }

    @Override
    public void cerrarIncidente(Incidente incidente){
        incidente.setEstado(EstadoIncidente.CERRADO);
    }

    @Override
    public void notificar(String mensaje){
        this.suscriptores.forEach(suscriptor -> {
            try {
                suscriptor.serNotificadoPor(this, mensaje);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public EntidadPrestadora(String nombre, String cuit, String personaAsignada, String telefono){
        this.setPersonaAsignada(personaAsignada);
        this.setNombre(nombre);
        this.setCuit(cuit);
        this.setTelefono(Integer.valueOf(telefono));
    }

}
