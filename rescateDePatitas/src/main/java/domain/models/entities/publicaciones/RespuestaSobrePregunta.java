package domain.models.entities.publicaciones;

public class RespuestaSobrePregunta {
    private Pregunta pregunta;
    private String respuesta;

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Pregunta getPregunta() {return pregunta; }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }
}
