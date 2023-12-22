package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter@Getter
@Entity
@DiscriminatorValue("organismoDeControl")
public class OrganismoDeControl extends PersonaJuridica{

    public OrganismoDeControl(String nombre, String cuit, String personaAsignada, String telefono){
        this.setNombre(nombre);
        this.setCuit(cuit);
        this.setPersonaAsignada(personaAsignada);
        this.setTelefono(Integer.valueOf(telefono));
    }

    public OrganismoDeControl() {

    }
}
