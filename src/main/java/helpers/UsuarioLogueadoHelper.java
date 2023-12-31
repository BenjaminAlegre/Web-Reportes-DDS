package helpers;

import db.EntityManagerHelper;
import model.entities.comunidad.Usuario;
import spark.Request;

public class UsuarioLogueadoHelper {

    public static Usuario usuarioLogueado(Request request) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Usuario.class, request.session().attribute("id"));
    }
}
