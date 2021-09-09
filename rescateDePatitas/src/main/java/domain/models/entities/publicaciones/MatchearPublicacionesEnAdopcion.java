package domain.models.entities.publicaciones;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import domain.models.entities.personas.Persona;
import org.quartz.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MatchearPublicacionesEnAdopcion implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        GestorDePublicaciones gestorDePublicaciones = GestorDePublicaciones.getInstancia();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //Recupera los datos de la publicacion
        Cuestionario preferencias = gson
                .fromJson((JsonReader) dataMap.get("preferencias"), RespuestaSobrePregunta.class);
        Cuestionario comodidades = gson
                .fromJson((JsonReader) dataMap.get("comodidades"), RespuestaSobrePregunta.class);
        Persona adoptante = gson.fromJson((JsonElement) dataMap.get("adoptante"), Persona.class);


        List<PublicacionEnAdopcion> publicacionesEnAdopcion = gestorDePublicaciones.getPublicacionesDeAdopcion();
        publicacionesEnAdopcion.stream().filter(publicacion ->
                compararpublicacion(preferencias, publicacion) && compararpublicacion(comodidades, publicacion));

        //TODO: Hacer que devuelva el hipervinculo a la publicacion. Probablemente sera posible en la ultima entrega
        ArrayList hypervinculosPublicacionesEnAdopcion = new ArrayList<>();
        publicacionesEnAdopcion.forEach(publicacion -> hypervinculosPublicacionesEnAdopcion.add(publicacion.getMascota().getApodo()));

        //Notifica a la persona unicamente si se encontraron publicaciones coincidentes en el periodo de tiempo
        if(!hypervinculosPublicacionesEnAdopcion.isEmpty()){
            adoptante.notificar(hypervinculosPublicacionesEnAdopcion);
        }


    }

    //Compara si para cada pregunta en el cuestionario1, existe una pregunta igual con respuesta igual
    public boolean compararpublicacion(Cuestionario cuestionario1, PublicacionEnAdopcion publicacionEnAdopcion) {
        Cuestionario cuestionario2 = publicacionEnAdopcion.getCuestionario();
        boolean answer = cuestionario1.getRespuestas().stream().allMatch(respuestaSobrePregunta -> {
            Optional<RespuestaSobrePregunta> match = cuestionario2.getRespuestas().stream()
                    .filter(respuesta -> respuesta.getPregunta() == respuestaSobrePregunta.getPregunta())
                    .findAny();
        if(!match.isPresent() || match.get().getRespuesta() != respuestaSobrePregunta.getRespuesta())
            return false;
        return true;
        });

        return answer;
    }
}
