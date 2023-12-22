package converters;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Horarios {

    public static String horaActualString(){
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
       return horaActual.format(formatter);
    }
}
