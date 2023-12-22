package controllers;

import DTO.ComunidadDTO;
import DTO.MiembroComunidadDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.entities.comunidad.Comunidad;
import model.entities.comunidad.MiembroComunidad;
import model.entities.comunidad.TipoMiembro;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioMiembroComunidad;
import model.repositorios.RepositorioMiembros;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MiembroController {

    RepositorioMiembros repoMiembros = new RepositorioMiembros();
    RepositorioComunidades repoComunidades = new RepositorioComunidades();

    RepositorioMiembroComunidad repoMiembroComunidad = new RepositorioMiembroComunidad();

    public ModelAndView pantallaComunidadesDeMiembro(Request req, Response res) {
        Integer idMiembro = req.session().attribute("idMiembro");

        List<MiembroComunidad> comunidades = obtenerMiembroComunidades(idMiembro);

        System.out.println("Comunidades de miembro: " + comunidades.size());

        Map<String, Object> model = new HashMap<>();
        model.put("comunidades", comunidades);
        model.put("miembroId", idMiembro);

        // Renderizar vista con Handlebars
          return new ModelAndView(model, "comunidadDeMiembroLiviano.hbs");
    }

    public ModelAndView cambiarTipoMiembroLiviano(Request req, Response res) {
        Integer idMiembro = req.session().attribute("idMiembro");
        String idComunidad = req.params(":comunidadId");
        String nuevoTipoMiembro = req.queryParams("nuevoTipoMiembro");

        MiembroComunidad miembroComunidad = repoMiembroComunidad.buscarPorMiembroYComunidad(Integer.parseInt(idComunidad), idMiembro);
        miembroComunidad.setTipoMiembro(TipoMiembro.valueOf(nuevoTipoMiembro));
        repoMiembroComunidad.actualizar(miembroComunidad);

        res.redirect("/miembroLiviano/comunidades");
        return null;
    }

    public List<MiembroComunidad> obtenerMiembroComunidades(Integer idMiembro) {

        return repoMiembroComunidad.obtenerMiembroComunidades(idMiembro);
    }
    public String obtenerComunidades(Request req, Response res) {
        Integer idMiembro = req.session().attribute("idMiembro");


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

        List<ComunidadDTO> comunidadesDTO = comunidades.stream()
                .map(c -> new ComunidadDTO(c.getId(), c.getNombre()))
                .collect(Collectors.toList());
        res.type("application/json");
        return new Gson().toJson(comunidadesDTO);
    }


    public ModelAndView pantallaMiembroComunidadPesado(Request req, Response res) {
        Integer idMiembro = req.session().attribute("idMiembro");
        List<MiembroComunidad> comunidades = obtenerMiembroComunidades(idMiembro);


        Map<String, Object> model = new HashMap<>();
        model.put("comunidades", comunidades);
        model.put("miembroId", idMiembro);

        return new ModelAndView(model, "comunidadDeMiembroPesado.hbs");
    }

    public String cambiarTipoMiembroPesado(Request req, Response res) {
        res.type("application/json");
        try {
            Integer idMiembro = req.session().attribute("idMiembro");
            String idComunidad = req.params(":comunidadId");

            // Recupera el cuerpo de la solicitud y analiza el JSON
            JsonObject json = new Gson().fromJson(req.body(), JsonObject.class);
            String nuevoTipoMiembro = json.get("nuevoTipoMiembro").getAsString();

            MiembroComunidad miembroComunidad = repoMiembroComunidad.buscarPorMiembroYComunidad(Integer.parseInt(idComunidad), idMiembro);
            miembroComunidad.setTipoMiembro(TipoMiembro.valueOf(nuevoTipoMiembro));
            repoMiembroComunidad.actualizar(miembroComunidad);
            return "{\"status\":\"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            res.status(500);
            return "{\"status\":\"error\", \"message\":\"" + e.getClass().getSimpleName() + ": " + e.getMessage() + "\"}";
        }
    }

    public List<MiembroComunidadDTO> miembroComunidadDTO(List<MiembroComunidad> comunidades){
        return comunidades.stream()
                .map(mc -> new MiembroComunidadDTO(mc.getComunidad().getNombre(), mc.getMiembro().getNombre(), mc.getTipoMiembro().toString()))
                .collect(Collectors.toList());
    }

}
