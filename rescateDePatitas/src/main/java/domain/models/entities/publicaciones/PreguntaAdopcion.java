package domain.models.entities.publicaciones;

import java.util.ArrayList;
import java.util.List;

public class PreguntaAdopcion {
    private String pregunta;
    private List<String> respuestas;



    public PreguntaAdopcion() {

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
}
