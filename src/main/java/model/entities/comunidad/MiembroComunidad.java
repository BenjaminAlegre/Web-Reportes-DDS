package model.entities.comunidad;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity@Getter@Setter
public class MiembroComunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Comunidad comunidad;

    @ManyToOne
    private Miembro miembro;

    @Enumerated(EnumType.STRING)
    private TipoMiembro tipoMiembro;

    public MiembroComunidad() {
    }
    public MiembroComunidad(Comunidad comunidad, Miembro miembro, TipoMiembro tipoMiembro) {
        this.comunidad = comunidad;
        this.miembro = miembro;
        this.tipoMiembro = tipoMiembro;
    }
}
