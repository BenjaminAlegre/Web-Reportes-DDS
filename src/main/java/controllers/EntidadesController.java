package controllers;

import model.entities.entidades.PersonaJuridica;
import model.repositorios.RepositorioEntidades;
import model.repositorios.RepositorioPersonasJuridicas;
import spark.Request;
import spark.Response;

import java.util.stream.Collectors;

public class EntidadesController {

    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
    RepositorioPersonasJuridicas repositorioPersonasJuridicas = new RepositorioPersonasJuridicas();

    public String obtenerEntidades(Request request, Response response) {

        return ExponedorDeRecursos.exponerRecurso(repositorioEntidades.buscarTodos().stream().map(e -> e.convertirADTO()).collect(Collectors.toList()), response );
    }

    public String obtenerEntidadesPorTipo(Request request, Response response) {
        String tipo = request.queryParams("tipoEntidad");
        return ExponedorDeRecursos.exponerRecurso(repositorioEntidades.buscarPorTipo(tipo).stream().map(e -> e.convertirADTO()).collect(Collectors.toList()), response);

    }

    public String obtenerEntidadesPorPrestadora(Request request, Response response) {
        String idPrestadora = request.queryParams("prestadora");
        PersonaJuridica entidad = repositorioPersonasJuridicas.buscarPorId(1); //TODO
        return ExponedorDeRecursos.exponerRecurso((entidad.getEntidades().stream().map(e -> e.convertirADTO()).collect(Collectors.toList())), response);
    }
}
