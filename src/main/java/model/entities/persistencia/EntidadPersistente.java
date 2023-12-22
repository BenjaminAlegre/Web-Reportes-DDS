package model.entities.persistencia;

import lombok.Getter;

import javax.persistence.*;

@Getter
@MappedSuperclass
public abstract class EntidadPersistente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false)
    private Integer id;

}
