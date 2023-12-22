package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;
import model.entities.persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Direccion extends EntidadPersistente {


    @Column
    private String calle;

    @Column
    private Integer altura;
}
