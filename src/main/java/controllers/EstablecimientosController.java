package controllers;

import model.entities.entidades.Entidad;
import model.repositorios.RepositorioEntidades;
import spark.Request;
import spark.Response;

import java.util.stream.Collectors;


public class EstablecimientosController {

    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();

    public String obtenerEstableciminetosDeEntidad(Request req, Response res){
        Entidad entidad = repositorioEntidades.buscarPorId(Integer.valueOf(req.queryParams("entidad")));

        return ExponedorDeRecursos.exponerRecurso(entidad.getEstablecimientos().stream().map(e -> e.convertirADTO()).collect(Collectors.toList()), res);
    }


}
