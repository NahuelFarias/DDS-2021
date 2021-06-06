package domain.models.entities.mascotas;

public class Caracteristica {
    private String descripcion;
    private String respuesta;

    public Caracteristica(String descripcion){
        setDescripcion(descripcion);
        setRespuesta(null);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
