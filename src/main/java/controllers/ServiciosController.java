package controllers;

import model.entities.servicio.Monitoreable;
import model.repositorios.RepositorioServicios;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

public class ServiciosController {
    RepositorioServicios repo = new RepositorioServicios();
    public String obtenerServiciosDeEstablecimiento(Request request, Response response){
        List<Monitoreable> monitoreables = repo.buscarServiciosPorIdEstablecimiento(Integer.valueOf(request.queryParams("establecimiento")));
        return ExponedorDeRecursos.exponerRecurso(monitoreables.stream().map(m -> m.convertirADTO()).collect(Collectors.toList()), response);
    }
}
