package ValidadorContraseñas.utils;

import java.util.regex.Pattern;

public class ValidadorNumeros implements Validacion{
    String regex = ".*[0-9].*";

    public ValidadorNumeros(){
    }

    @Override
    public boolean validar(String contrasenia) {
        return Pattern.matches(regex, contrasenia);
    }


}
