package Cron;

import com.mashape.unirest.http.exceptions.UnirestException;
import converters.Horarios;
import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Miembro;
import model.entities.notificacion.Incidente;
import model.repositorios.RepositorioMiembros;
import model.repositorios.incidentes.RepositorioIncidentes;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class NotificadorPorHorario implements Job {

    RepositorioMiembros repoMimebros = new RepositorioMiembros();
    RepositorioIncidentes repoIncidentes = new RepositorioIncidentes();


    public void execute(JobExecutionContext context) {//TODO
        //obtener horario
        String hora = Horarios.horaActualString();
        //traer los miembros con el horario de notificacion correrpondiente
        List<Miembro> miembrosAnorificar = this.repoMimebros.burcarPorHorario(hora);
        try {
            this.notifacarMiembros(miembrosAnorificar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notifacarMiembros(List<Miembro> miembrosAnorificar) throws MessagingException, UnirestException, IOException, GeneralSecurityException {

        for (Miembro m : miembrosAnorificar
        ) {
            //trer incidentes reportados de cada comunidad
            List<Incidente> incidentes = this.obtenerIncidentes(m);
            String mensaje = this.armarMensaje(incidentes);
            m.serNotificadoPor(null,mensaje );
        }
    }



    private List<Incidente> obtenerIncidentes(Miembro m) {
        return m.getComunidades().stream().flatMap(c -> c.incidentesPorHorario(m).stream()).collect(Collectors.toList());
    }

    private String armarMensaje(List<Incidente> incidentes) {
        String mensaje ="Notificacion De Incidentes de Servicios:\n";
        for (Incidente i: incidentes) {
            mensaje.concat(i.descripcionServicioAfectado());
        }
        return mensaje;
    }



}
