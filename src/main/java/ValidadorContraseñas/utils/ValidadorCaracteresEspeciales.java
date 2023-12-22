package ValidadorContraseñas.utils;

import java.util.regex.Pattern;

public class ValidadorCaracteresEspeciales implements Validacion{
    String regex = ".*[^\\w].*";

    public ValidadorCaracteresEspeciales(){
    }

    @Override
    public boolean validar(String contrasenia) {
        return Pattern.matches(regex, contrasenia);
    }


}
