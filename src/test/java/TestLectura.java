import cargaDeDatosMasiva.Importador;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;


import java.io.IOException;

public class TestLectura {

    @Test
    public void leer() throws Exception {
        Importador importador = new Importador();
        importador.generarObjetos("archivo.csv");
    }
}
