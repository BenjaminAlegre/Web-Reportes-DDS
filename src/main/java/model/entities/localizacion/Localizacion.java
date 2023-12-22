package model.entities.localizacion;

import lombok.Getter;
import lombok.Setter;
import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "tipo")
public abstract class Localizacion {

    @Id
    public Integer id;

    @Column
    public String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "localizacion", cascade = CascadeType.ALL)
    private List<Entidad> entidades;



}
