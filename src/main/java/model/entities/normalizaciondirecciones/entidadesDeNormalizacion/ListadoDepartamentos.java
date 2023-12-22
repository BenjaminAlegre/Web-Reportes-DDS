package model.entities.normalizaciondirecciones.entidadesDeNormalizacion;

import lombok.Getter;
import lombok.Setter;
import model.entities.localizacion.Departamento;
import model.entities.localizacion.Provincia;
import model.entities.persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ListadoDepartamentos extends EntidadPersistente {

    public List<Departamento> departamentos;

    public ListadoDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public Optional<Departamento> buscarDepartamento(String  nombre){
        return departamentos.stream()
                .filter(p -> p.nombre.equals(nombre))
                .findFirst();
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }


    public List<NombreDepartamento> devolverNombres(){
        List<NombreDepartamento> departamentos = new ArrayList<>();
        for(Departamento m:this.departamentos){
            departamentos.add(new NombreDepartamento(m.nombre));
        }
        return departamentos;
    }




    class NombreDepartamento{
        public String nombre;

    public NombreDepartamento(String nombre) {
        this.nombre = nombre;
    }
}
}
