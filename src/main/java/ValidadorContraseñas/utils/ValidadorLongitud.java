package ValidadorContraseñas.utils;

import lombok.Getter;
import lombok.Setter;

public class ValidadorLongitud implements Validacion{
    @Getter @Setter private Integer longitud;

    public ValidadorLongitud(Integer longitud){
        this.setLongitud(longitud);
    }

    @Override
    public boolean validar( String contrasenia) {
        return contrasenia.length() >= this.longitud;
    }


}
