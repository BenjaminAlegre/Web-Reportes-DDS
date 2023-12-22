package cargaDeDatosMasiva;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public interface ImportadorCSVAdaptada {
    public void generarObjetos(String path) throws Exception;
}
