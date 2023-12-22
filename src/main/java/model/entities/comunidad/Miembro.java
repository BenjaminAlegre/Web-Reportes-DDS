package model.entities.comunidad;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.Getter;
import lombok.Setter;
import model.entities.entidades.EntidadPrestadora;
import model.entities.localizacion.Localizacion;
import model.entities.notificacion.*;
import model.entities.persistencia.EntidadPersistente;
import model.entities.servicio.Monitoreable;
import javax.mail.MessagingException;
import javax.persistence.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Miembro extends EntidadPersistente implements Reportador, Observador {

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String telefono;

    @Column
    private String mail;

//    @Enumerated(EnumType.STRING)
//    private TipoMiembro tipo;

  

    @ManyToMany(mappedBy = "miembros")
    private List<Monitoreable> moritoreable = new ArrayList<>();

    @ManyToMany(mappedBy = "miembros")
    private List<Comunidad> comunidades = new ArrayList<>();

    @OneToMany(mappedBy = "miembro")
    private List<MiembroComunidad> comunidadesMiembro = new ArrayList<>();

    @ManyToMany(mappedBy = "administradores")
    private List<Comunidad> comunidadesAdministradas = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MedioNotificacion medioNotificacion;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Usuario usuario;

    @ElementCollection
    @CollectionTable(name = "horarios", joinColumns = @JoinColumn(name = "prestador_id"))
    private List<String> horariosDeNotificacion = new ArrayList<>();

    @ManyToMany(mappedBy = "suscriptores")
    private List<EntidadPrestadora> suscripcionesAEntidadesPrestadoras = new ArrayList<>();; //podria ser un Set<EntidadPrestadora>

    private LocalDate ultimaNotificacion;

    public Miembro(String nombre, String apellido, String mail, String telefono){
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;

    }

    public Miembro() {

    }


//    public void asociarseAComunidad(Comunidad comunidad) {
//        comunidades.add(comunidad);
//    }
//
//    public void desasociarseDeComunidad(Comunidad comunidad) {
//        comunidades.remove(comunidad);
//    }

    public void asociarseAComunidad(Comunidad comunidad, TipoMiembro tipoMiembro) {
        MiembroComunidad miembroComunidad = new MiembroComunidad(comunidad, this, tipoMiembro);
        comunidadesMiembro.add(miembroComunidad);
        comunidad.agregarMiembro(this);

    }

    @Override
    public void generarIncidente(Monitoreable servicioAfectado, String observaciones) {
       new Incidente(this.getId().toString(),servicioAfectado, observaciones).guardate();
    }


    @Override
    public void cerrarIncidente(Incidente incidente) {
        incidente.setEstado(EstadoIncidente.CERRADO);
    }

    public void suscribirseAEntidad(EntidadPrestadora entidad){//TODO posible cambio a entidad
        entidad.agregarSuscriptor(this);
        suscripcionesAEntidadesPrestadoras.add(entidad);
    }

    @Override
    public void serNotificadoPor(Observable observable, String mensaje) throws IOException, MessagingException, UnirestException, GeneralSecurityException { // usar case es ma extensible y declarativo, lo mejor seria objetos pero como solo son dos medios, case
// hay que ver que se manda el observable
        Notificador notificador = new Notificador();
        notificador.notificar(mensaje, this);
    }


    public String contacto() {
        switch (this.medioNotificacion){
            case WHATSAPP: return this.telefono;
            case CORREO_ELECTRONICO: return this.mail;
            default: return null;
        }
    }
}
