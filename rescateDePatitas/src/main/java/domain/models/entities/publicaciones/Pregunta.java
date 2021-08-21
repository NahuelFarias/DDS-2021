package domain.models.entities.publicaciones;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
    private String pregunta;
    private List<String> respuestas;
    private Boolean visible;

    public Pregunta() {
        this.respuestas = new ArrayList<String>();
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }

    public void agregarRespuesta(String respuesta) {
        respuestas.add(respuesta);
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getVisible() {
        return visible;
    }
}
