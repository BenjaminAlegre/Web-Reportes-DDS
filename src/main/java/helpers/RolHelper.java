package helpers;

import model.entities.comunidad.Rol;
import spark.Request;

public class RolHelper {

    public static Boolean usuarioTieneRol(Request request, Rol rol) {
        return UsuarioLogueadoHelper
                .usuarioLogueado(request)
                .getRol() == rol;
    }
}
