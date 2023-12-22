import model.entities.comunidad.Miembro;
import org.junit.jupiter.api.Test;

import java.awt.image.MemoryImageSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TestLocalDate {

    @Test
    public void imprimirLocalDate(){
        System.out.println(LocalDate.now());
    }

    @Test
    public void imprimirTime(){
        DateFormat formato = new SimpleDateFormat("HH:mm");
        System.out.println(formato.format(new Date()));
    }


    @Test
    public void probarMiembroMockeado(){
        Miembro miembro = new Miembro();
        miembro.setApellido("Gorgonzola");
        miembro.getHorariosDeNotificacion().add("18:15");
        miembro.getHorariosDeNotificacion().add("12:15");

        if(miembro.getHorariosDeNotificacion().contains("18:15"))
            System.out.println("bien");
        else
            System.out.println("mal");

    }
}
