package controllers.utils;

import cargaDeDatosMasiva.Importador;
import cargaDeDatosMasiva.ImportadorCSVAdaptada;
import controllers.AdministradorController;
import lombok.Getter;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CargadorMasivo extends Thread {

    ImportadorCSVAdaptada importador = new Importador();
    private Request request;
    private Response response;
    private Path tempFile;

    public CargadorMasivo(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    public void run() {
        tempFile = this.armarDirectorio();
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ARMO DIRECTORIO....");
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        this.cargarBD(request);

    }

    private void cargarBD(Request request) {
        try (InputStream input = request.raw().getPart("archivoInput").getInputStream()) {

            try {
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: LLAMA A IMPORTADOR");
                importador.generarObjetos(tempFile.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: DATOS CARGADOS");
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private Path armarDirectorio() {
        File uploadDir = new File("upload");
        uploadDir.mkdir();
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

}
