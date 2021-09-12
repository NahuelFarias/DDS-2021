package domain.models.entities.rol;

import domain.models.entities.Persistente;
import domain.models.entities.mascotas.*;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;
import domain.models.entities.publicaciones.Cuestionario;
import domain.models.entities.publicaciones.PublicacionGenerica;
import domain.models.entities.publicaciones.RespuestaSobrePregunta;
import domain.models.repositories.RepositorioDeUsuarios;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Rol extends Persistente {
    @Column(name = "tipoDeRol")
    private String tipo;
    @ManyToOne
    private Persona persona;

    public Rol(String rescatista) {
        this.tipo = rescatista;
    }

    public String getTipo() {
        return this.tipo;
    }

}
