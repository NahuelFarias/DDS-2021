package domain.models.entities.publicaciones;

import java.util.List;

public class Cuestionario {
    private List<Pregunta> preguntas;
    private List<RespuestaSobrePregunta> respuestas;
    private String tipo;

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void setRespuestas(List<RespuestaSobrePregunta> respuestas) {
        this.respuestas = respuestas;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<RespuestaSobrePregunta> getRespuestas() {
        return respuestas;
    }
}
