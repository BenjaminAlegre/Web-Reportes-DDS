package converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.DayOfWeek;

@Converter(autoApply = true)
public class DiaDeLaSemanaConverter implements AttributeConverter<DayOfWeek, String> {

    @Override
    public String convertToDatabaseColumn(DayOfWeek dayOfWeek) {
        String dia = null;
        switch (dayOfWeek) {
            case MONDAY: dia = "LUNES";
                break;
            case TUESDAY: dia = "MARTES";
                break;
            default: dia = null;
        }
        return dia;
    }

    @Override
    public DayOfWeek convertToEntityAttribute(String s) {
        if(s == null)
            return null;
        DayOfWeek dia = null;
        switch (s) {
            case "LUNES": dia = DayOfWeek.MONDAY;
                break;
            default: throw new IllegalArgumentException(s + " no se puede convertir en u DayOfWeek");
        }
        return dia;
    }
}
