package model.entities.normalizaciondirecciones.entidadesDeNormalizacion;


import lombok.Getter;
import lombok.Setter;
import model.entities.localizacion.Departamento;
import model.entities.localizacion.Municipio;
import model.entities.persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListadoMunicipios extends EntidadPersistente {

    public Departamento departamento;


    public List<Municipio> municipios;



    public String nombreDepartamento() {
        return this.departamento.nombre;
    }

}
