package domain.models.entities.publicaciones;

public class RespuestaSobrePregunta {
    private PreguntaAdopcion pregunta; //TODO: Ver las preferencias y comodidades son lo mismo
    private String respuesta;

    public void setPregunta(PreguntaAdopcion pregunta) {
        this.pregunta = pregunta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }
}
