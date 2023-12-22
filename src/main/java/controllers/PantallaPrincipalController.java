package controllers;

import model.entities.comunidad.MiembroComunidad;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PantallaPrincipalController {

    MiembroController controllerMimebro = new MiembroController();
    public ModelAndView pantallaPaginaPrincipal(Request req, Response res) {
        return new ModelAndView(null, "paginaPrincipal.hbs");
    }

    public ModelAndView pantallaPrincipalAdministrador(Request req, Response res) {
        return new ModelAndView(null, "principalAdministrador.hbs");
    }

    public ModelAndView pantallaPrincipalEntidad(Request req, Response res) {

        return new ModelAndView(null, "principalEntidadPrestadora.hbs");
    }

    public ModelAndView pantallaPrincipalMiembro(Request req, Response res) {
        Integer idMiembro = req.session().attribute("idMiembro");
        List<MiembroComunidad> comunidades = controllerMimebro.obtenerMiembroComunidades(idMiembro);
        Map<String, Object> model = new HashMap<>();
        model.put("comunidades", comunidades);
        model.put("miembroId", idMiembro);
        return new ModelAndView(model, "principalMiembro.hbs");
    }
}
