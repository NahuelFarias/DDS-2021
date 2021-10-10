package domain.models.entities.rol;

import domain.models.entities.Persistente;
import domain.models.entities.personas.Persona;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Rol extends Persistente {
    @Column(name = "tipoDeRol")
    private String tipo;
    @Column(name = "class_image")
    private String classImage;
    @ManyToOne
    private Persona persona;

    public Rol(String rescatista) {
        this.tipo = rescatista;
        setClassImage();
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setClassImage() {
        if(this.tipo == "Dueño") {
            this.classImage = "fas fa-dog";
        } else if(this.tipo == "Voluntario") {
            this.classImage = "fas fa-paw";
        } else if(this.tipo == "Rescatista") {
            this.classImage = "fab fa-gratipay";
        } else if(this.tipo == "Administrador") {
            this.classImage = "fas fa-user-cog";
        }
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getClassImage() {
        return classImage;
    }
}
