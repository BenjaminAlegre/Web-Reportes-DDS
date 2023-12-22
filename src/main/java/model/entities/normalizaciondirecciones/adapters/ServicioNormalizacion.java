package model.entities.normalizaciondirecciones.adapters;

import model.entities.localizacion.Departamento;
import model.entities.localizacion.Provincia;
import model.entities.normalizaciondirecciones.entidadesDeNormalizacion.ListadoDepartamentos;
import model.entities.normalizaciondirecciones.entidadesDeNormalizacion.ListadoMunicipios;
import model.entities.normalizaciondirecciones.entidadesDeNormalizacion.ListadoMunicipiosProvincia;
import model.entities.normalizaciondirecciones.entidadesDeNormalizacion.ListadoPosiblesDirecciones;

import java.io.IOException;
import java.util.List;

public interface ServicioNormalizacion {
    public List<Provincia> listadoProvincias() throws IOException;//

    public ListadoMunicipios listadoMunicipiosDepartamento(Departamento departamento) throws IOException;

    public ListadoMunicipiosProvincia listadoMunicipiosProvincia(Provincia provincia) throws IOException;

    public List<Departamento> listadoDepartamentos(Provincia provincia) throws IOException;

    public ListadoPosiblesDirecciones normalizarDireccion(String direccionIngresada) throws IOException;

}
