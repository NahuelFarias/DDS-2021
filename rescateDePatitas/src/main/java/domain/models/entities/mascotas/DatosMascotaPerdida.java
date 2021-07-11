package domain.models.entities.mascotas;

import java.util.List;

public class DatosMascotaPerdida {
    private List<Foto> fotos;
    private String descripcion;
    private Lugar lugar;

    public DatosMascotaPerdida(List<Foto> fotos, String descripcion, Lugar lugar) {
        setFotos(fotos);
        setDescripcion(descripcion);
        setLugar(lugar);
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public List<Foto> getFotos() {
        return fotos;
    }
}
