package cargaDeDatosMasiva;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.Getter;
import lombok.Setter;
import model.entities.entidades.*;
import model.repositorios.RepositorioPersonasJuridicas;


import java.io.*;

@Getter @Setter
public class Importador implements ImportadorCSVAdaptada {
    private Integer contadorFilas = 0;
    private RepositorioPersonasJuridicas repositorioPersonasJuridicas = new RepositorioPersonasJuridicas();

    public void generarObjetos(String path) throws Exception {
        this.leerArchivo(path);
    }

    private void leerArchivo(String path) throws Exception {
        String archCSV = path;
        CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
        CSVReader reader = new CSVReaderBuilder(new FileReader(archCSV)).withCSVParser(conPuntoYComa).build();
        reader.readNext();
        String[] fila  = reader.readNext();
        while (fila != null) {

            System.out.println("::::::::::::::::::::::::::::::::::::::::::::"+fila[0]+" ................. " +fila.length);
            this.contadorFilas++;
            Integer idPersonaJuridica = existeEnDB(fila[2]);
            if( idPersonaJuridica== null)
                this.crearInstancia(fila);
            fila = reader.readNext();
        }
        reader.close();
    }

    private void agregarEntidad(String[] fila, Integer idPersonaJuridica){
        PersonaJuridica  personaJuridica = repositorioPersonasJuridicas.buscarPorId(idPersonaJuridica);

    }

    private Integer existeEnDB(String cuit) {
        return this.repositorioPersonasJuridicas.existeCuit(cuit);
    }

    private void crearInstancia(String[] fila) throws Exception {

        PersonaJuridica entidad = null;
        switch (fila[0]) {
            case "ORGANISMO_DE_CONTROL":
                entidad = new OrganismoDeControl(fila[1], fila[2], fila[3], fila[4]);
                break;
            case "ENTIDAD_PRESTADORA":
                entidad = new EntidadPrestadora(fila[1],fila[2],fila[3],fila[4]);

            default:
                System.out.println("no se pudo crear entidad de fila " + contadorFilas);
        }

       // repositorioPersonasJuridicas.guardar(entidad);
        //      System.out.println(entidad.toString());

    }

//    private EntidadPrestadora crearEntidadPrestadora(String[] fila) throws Exception {
//        return new EntidadPrestadora(fila[1],fila[2],fila[3],fila[4]);
//
//    }
//
//    private Entidad crearLineaDeTrasporte(String[] fila)  throws Exception{
//        return  new LineaDeTransporte(fila);
//    }
//
//    private Entidad crearOrganizacion(String[] fila) throws Exception {
//        return new Organizacion(fila);
//    }


    public Importador() {

    }
}
