package model.entities.notificacion;

import model.entities.servicio.Monitoreable;

import java.io.IOException;


public interface Reportador {

      public void generarIncidente(Monitoreable servicioAfectado, String Observaciones);

//    public Incidente generarIncidente(Monitoreable servicioAfectado, String observaciones,
//                                      EstadoIncidente estado, LocalDate horarioApertura, LocalDate horarioCierre,
//                                      String idReportador, Entidad entidadAfectada); // string reportador se desnormaliza y se pesiste solo el id sera un atributo de session
//                                                                                        // que se puede tomar en cuallqueirmomento en el controller

      public void cerrarIncidente(Incidente incidente);


}
