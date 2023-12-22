package controllers;

import DTO.ComunidadDTO;
import DTO.IncidenteDTO;
import com.google.gson.Gson;
import model.entities.comunidad.Comunidad;
import model.entities.comunidad.MiembroComunidad;
import model.entities.notificacion.Incidente;
import services.IncidenteService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import static model.entities.notificacion.EstadoIncidente.ACTIVO;

public class IncidentesController {

    private IncidenteService incidenteService = new IncidenteService();


    public ModelAndView pantallaAperturaIncidentes(Request req, Response res){
        return new ModelAndView(null, "aperturaIncidentes.hbs");
    }

    public  ModelAndView buscarIncidente(Request req, Response res){
        return  new ModelAndView(null, "buscarIncidente.hbs");
    }

    public  ModelAndView mostrarIncidente(Request req, Response res){
        return  new ModelAndView(null, "mostrarIncidente.hbs");
    }

    public Response registrarIncidentePesado(Request req, Response res) {
        incidenteService.guardarIncidenteJson(req);
        res.status(200);
        res.body("Incidente registrado");
        return res;
    }
    public ModelAndView registrarIncidente(Request req, Response res) {
        incidenteService.guardarIncidente(req);
        res.redirect("/aperturaIncidente/");
        return null;
    }


    public ModelAndView mostrarTodosIncidentes(Request req, Response res) {
//        List<Incidente> incidentes = incidenteService.obtenerTodos();

        List<Incidente> incidentes = incidenteService.obtenerPorEstado("ACTIVO");
        System.out.println("Incidentes: " + incidentes);

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("incidentes", incidentes);

        return new ModelAndView(modelo, "mostrarIncidente.hbs");
    }

    public ModelAndView mostrarIncidentesUsuario(Request req, Response res) {
//        List<Incidente> incidentes = incidenteService.obtenerTodos();
        Integer idMiembro = req.session().attribute("idMiembro");

        System.out.println("IdUser: " + idMiembro);
        List<Incidente> incidentes = incidenteService.obtenerPorMiembro(idMiembro);
        for (Incidente i : incidentes) {
            System.out.println("IdIncidente: " + i.getId());
            System.out.println("Comunidades: " + i.getComunidades().get(0).getNombre());
        }

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("incidentes", incidentes);
        modelo.put("idUsuario", idMiembro);

        return new ModelAndView(modelo, "mostrarIncidenteMiembro.hbs");
    }


    public ModelAndView cerrarIncidente(Request req, Response res) {
        Integer id = Integer.parseInt(req.params(":id"));
        try {
            incidenteService.cerrarIncidente(id);
            res.redirect("/mostrarTodosIncidentes");
        } catch (Exception e) {
            e.printStackTrace(); // Para depuración
            res.status(500); // Indica un error interno del servidor
        }
        return null;
    }

    public ModelAndView pantallaBuscarIncidentesPorEstado(Request req, Response res) {
        return new ModelAndView(null, "buscarIncidente.hbs");
    }

    public ModelAndView pantallaBuscarIncidenteComunidad(Request req, Response res) {
        Integer idMiembro = req.session().attribute("idMiembro");

        Map<String, Object> model = new HashMap<>();
        model.put("idUsuario", idMiembro);

        return new ModelAndView(model, "buscarIncidenteComunidad.hbs");
    }



    public ModelAndView pantallaCargaMasiva(Request req, Response res) {
        return new ModelAndView(null, "cargaMasiva.hbs");
    }
    public ModelAndView pantallaRankings(Request req, Response res) {
        return new ModelAndView(null, "rankings.hbs");
    }


    public ModelAndView pantallaResultadoBusqueda(Request req, Response res) {
        return new ModelAndView(null, "resultadoBusqueda.hbs");
    }
    public ModelAndView pantallaSugerenciaRevisionIncidente(Request req, Response res) {
        return new ModelAndView(null, "sugerenciaRevisionIncidente.hbs");
    }

    public String mostrarIncidentesPorEstado(Request req, Response res) {
        try {
            String estado = req.queryParams("estado");
            List<IncidenteDTO> incidentesDTO = incidenteService.obtenerPorEstadoToDTO(estado);
            res.type("application/json");
            return new Gson().toJson(incidentesDTO);
        } catch (Exception e) {
            res.status(500);
            return "Error al procesar la solicitud: " + e.getMessage();
        }
    }

    public String mostrarIncidentesEstadoComunidad(Request req, Response res) {
        try {
            String estado = req.queryParams("estado");
            String comunidad = req.queryParams("comunidad");

            System.out.println("Estado: " + estado);
            System.out.println("Comunidaad: " + comunidad);

            List<IncidenteDTO> incidentesDTO = incidenteService.obtenerPorEstadoYComunidadToDTO(estado, comunidad);
            res.type("application/json");

            System.out.println("Json: " + new Gson().toJson(incidentesDTO));

            return new Gson().toJson(incidentesDTO);
        } catch (Exception e) {
            res.status(500);
            return "Error al procesar la solicitud: " + e.getMessage();
        }
    }

    public ModelAndView pantallaInicio(Request req, Response res) {
        return new ModelAndView(null, "iniciarSesion.hbs");
    }


}
