package ValidadorContraseñas.utils;

import java.util.*;

public class Validador {
    private List<Validacion> listaDeValidaciones;


    public Validador(Validacion... validaciones) {
        this.listaDeValidaciones = new ArrayList<>();
        for (Validacion v : validaciones) {
            this.listaDeValidaciones.add(v);
        }
    }

    public boolean validarContrasenia(String contrasenia) {
        boolean valida = true;
        for (int i = 0; i < this.listaDeValidaciones.size(); i++) {
           if(!listaDeValidaciones.get(i).validar(contrasenia)) {
               valida = false;
           }
        }
        return valida;
    }
}