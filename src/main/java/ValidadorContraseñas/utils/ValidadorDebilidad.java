package ValidadorContraseñas.utils;



import ValidadorContraseñas.external.GestorArchivoContrasenias;
import ValidadorContraseñas.external.Nodo;

import java.io.IOException;

public class ValidadorDebilidad implements Validacion{
    public GestorArchivoContrasenias gestorDeArchivoDeContrasenias ;// cambiar a private

    public ValidadorDebilidad(String path, Validacion ... validaciones) throws IOException {
        this.gestorDeArchivoDeContrasenias = new GestorArchivoContrasenias(path, validaciones);
        this.gestorDeArchivoDeContrasenias.obtenerContraseniasUtiles();
    }
    public boolean validar(String contrasenia){
        boolean coincide = false;
        Nodo aux = gestorDeArchivoDeContrasenias.arbolDeBusqueda.raiz;
        while(aux!= null && !coincide){
            if(contrasenia.compareTo(aux.info) == 0){
                coincide = true;
            }
            else{
                if(contrasenia.compareTo(aux.info) < 0)
                    aux = aux.izquierda;
                else
                    aux = aux.derecha;
            }
        }
        return !coincide;
    }

}
