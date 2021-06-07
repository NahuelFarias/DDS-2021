package domain.models.entities.mascotas;


public class CaracteristicaConRta {
    private String descripcion;
    private String respuestaElegida;

    public CaracteristicaConRta(String descripcion, String respuestaElegida){
        this.descripcion = descripcion;
        this.respuestaElegida = respuestaElegida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRespuestaElegida() { return this.respuestaElegida; }

}
