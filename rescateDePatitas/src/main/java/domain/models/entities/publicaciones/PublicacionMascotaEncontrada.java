package domain.models.entities.publicaciones;

import domain.models.entities.mascotas.Foto;
import domain.models.entities.mascotas.Lugar;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.personas.Persona;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "publicacion_mascota_encontrada")
public class PublicacionMascotaEncontrada extends PublicacionGenerica{
    @OneToMany(mappedBy = "publicacionMascotaEncontrada", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Foto> fotos;
    @OneToOne
    private Lugar lugar;
    @ManyToOne
    private Organizacion organizacion;
    // TODO esto funciona?
    @ManyToOne
    private Persona rescatista;

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion){
        this.organizacion = organizacion;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public Persona getRescatista() {
        return rescatista;
    }

    public void setRescatista(Persona rescatista) {
        this.rescatista = rescatista;
    }
}
