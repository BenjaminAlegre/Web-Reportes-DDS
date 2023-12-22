package services;

import DTO.IncidenteDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Miembro;
import model.entities.comunidad.MiembroComunidad;
import model.entities.entidades.Entidad;
import model.entities.entidades.Establecimiento;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.Monitoreable;
import model.entities.servicio.Servicio;
import model.repositorios.*;
import model.repositorios.incidentes.RepositorioIncidentes;
import spark.Request;
import spark.Response;


import javax.persistence.EntityNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class IncidenteService {
    private RepositorioIncidentes repoIncidentes = new RepositorioIncidentes();
    private RepositorioServicios repoServicios = new RepositorioServicios();
    private RepositorioEntidades repoEntidades = new RepositorioEntidades();
    private RepositorioEstablecimientos repoEstablecimientos = new RepositorioEstablecimientos();
    private RepositorioComunidades repoComunidades = new RepositorioComunidades();
    private RepositorioMiembroComunidad repoMiembroComunidad = new RepositorioMiembroComunidad();
    private RepositorioMiembros repoMiembros = new RepositorioMiembros();

    public void guardarIncidente(Request req) {

        try {
            Integer servicioId = Integer.parseInt(req.queryParams("servicioId"));
            String observaciones = req.queryParams("observaciones");
            String entidadIdStr = req.queryParams("entidadId");

            Integer entidadId = null;
            if (entidadIdStr != null && !entidadIdStr.isEmpty()) {
                entidadId = Integer.parseInt(entidadIdStr);
            } else{
                throw new Exception("Error ID entidad");
            }

            Entidad entidadAfectada = null;
            if (entidadId != null) {
                entidadAfectada = repoEntidades.buscarPorId(entidadId);
                if (entidadAfectada == null) {
                    throw new Exception("No se encontro una entidad");
                }
            } else{
                throw new Exception("Error id entidad null");
            }

            Servicio servicio = repoServicios.buscarPorId(servicioId);

            LocalDateTime horarioApertura = LocalDateTime.now();

            Incidente incidente = new Incidente();
            incidente.setEntidadAfectada(entidadAfectada);
            incidente.setObservaciones(observaciones);
            incidente.setHorarioApertura(horarioApertura);
            incidente.setServicioAfectado(servicio);
            //relacion entre monitoreable afectado y comunidad

           incidente.agregarIncidenteAComunidad();

            // Persistir el objeto Incidente
            repoIncidentes.guardar(incidente);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void guardarIncidenteJson(Request req) {

        try {
            JsonObject jsonBody = new Gson().fromJson(req.body(), JsonObject.class);

            Integer servicioId = jsonBody.get("servicioId").getAsInt();
            String observaciones = jsonBody.get("observaciones").getAsString();
            Integer entidadId = jsonBody.get("entidadId").getAsInt();

            if (entidadId != null) {
                    System.out.println("Servicio: " + servicioId);
                    System.out.println("Observaciones: " + observaciones);
                    System.out.println("Entidad: " + entidadId);
            } else {
                throw new IllegalArgumentException("La clave 'entidades' no puede ser nula en el JSON");
            }

            Entidad entidadAfectada = null;
            if (entidadId != null) {
                entidadAfectada = repoEntidades.buscarPorId(entidadId);
                if (entidadAfectada == null) {
                    throw new Exception("No se encontro una entidad");
                }
            } else{
                throw new Exception("Error id entidad null");
            }

            Servicio servicio = repoServicios.buscarPorId(servicioId);

            LocalDateTime horarioApertura = LocalDateTime.now();

            Incidente incidente = new Incidente();
            incidente.setEntidadAfectada(entidadAfectada);
            incidente.setObservaciones(observaciones);
            incidente.setHorarioApertura(horarioApertura);
            incidente.setServicioAfectado(servicio);
            //relacion entre monitoreable afectado y comunidad

            incidente.agregarIncidenteAComunidad();

            // Persistir el objeto Incidente
            repoIncidentes.guardar(incidente);
            System.out.println("Incidente guardado");

//            JsonObject incidenteJson = new JsonObject();
//            incidenteJson.addProperty("servicioId", incidente.getServicioAfectado().getId());
//            incidenteJson.addProperty("entidadId", incidente.getEntidadAfectada().getId());
//            incidenteJson.addProperty("observaciones", incidente.getObservaciones());
//            return incidenteJson;
        } catch (NumberFormatException | EntityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void cerrarIncidente(Integer id) {
        Incidente incidente = repoIncidentes.buscarPorId(id);
        if (incidente != null) {
            incidente.setEstado(EstadoIncidente.CERRADO);
            incidente.setHorarioCierre(LocalDateTime.now());
            repoIncidentes.actualizar(incidente);
        }else {
            throw new RuntimeException("No se encontro el incidente");
        }
    }

    public List<Incidente> obtenerTodos() {
        return repoIncidentes.obtenerTodos();
    }

    public List<Incidente> obtenerPorEstado(String estado) {
        return repoIncidentes.buscarPorEstado(estado);
    }

    public List<IncidenteDTO> obtenerPorEstadoToDTO(String estado) {
        List<Incidente> incidentes = repoIncidentes.buscarPorEstado(estado);
        return incidentes.stream()
                .map(incidente -> new IncidenteDTO(incidente.getId(), incidente.getEstado().toString(), incidente.getObservaciones(), incidente.getHorarioApertura(), incidente.getHorarioCierre() ))
                .collect(Collectors.toList());
    }

    public List<IncidenteDTO> obtenerPorEstadoYComunidadToDTO(String estado, String idComunidad) {
        Comunidad comunidad = repoComunidades.buscarPorId(Integer.parseInt(idComunidad));
        List<Incidente> incidentes = repoIncidentes.buscarPorEstadoYComunidad(estado, comunidad);
        return incidentes.stream()
                .map(incidente -> new IncidenteDTO(incidente.getId(), incidente.getEstado().toString(), incidente.getObservaciones(), incidente.getHorarioApertura(), incidente.getHorarioCierre() ))
                .collect(Collectors.toList());
    }

    public List<Incidente> obtenerPorMiembro(Integer idMiembro) {
        List<MiembroComunidad> miembroComunidades = repoMiembroComunidad.obtenerMiembroComunidades(idMiembro);
        System.out.println("Comunidades de miembro: " + miembroComunidades.size());
        //Un while que recorra las comunidades y que por cada una muestre en un System.out sus datos
        for (MiembroComunidad mc : miembroComunidades) {
            System.out.println("Comunidad: " + mc.getComunidad().getNombre());
            System.out.println("Tipo de miembro: " + mc.getTipoMiembro());
            System.out.println("Miembro: " + mc.getMiembro().getNombre());
        }
        List<Comunidad> comunidades = miembroComunidades.stream()
                .map(mc -> mc.getComunidad())
                .collect(Collectors.toList());

        return repoIncidentes.buscarPorComunidad(comunidades);
    }



}
