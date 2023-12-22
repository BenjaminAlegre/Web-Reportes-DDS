package ValidadorContraseñas.utils;

import java.util.regex.Pattern;

public class ValidadorMayusuculas implements Validacion{
  String regex = ".*[A-Z].*";

    public ValidadorMayusuculas() {
    }

    @Override
    public boolean validar(String contrasenia) {
       return Pattern.matches(regex, contrasenia);
    }


}
