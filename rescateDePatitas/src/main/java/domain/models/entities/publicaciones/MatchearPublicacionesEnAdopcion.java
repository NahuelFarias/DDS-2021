package domain.models.entities.publicaciones;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import domain.models.entities.personas.Persona;
import org.quartz.*;

import java.util.List;

public class MatchearPublicacionesEnAdopcion implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<RespuestaSobrePregunta> preferenciasYComodidades = gson
                .fromJson((JsonReader) dataMap.get("preferenciasYComodidades"), RespuestaSobrePregunta.class);
        Persona adoptante = gson.fromJson((JsonElement) dataMap.get("adoptante"), Persona.class);

        //Invocar metodo en la persona para setearle una lista con las publicaciones matcheadas??

        System.out.println(adoptante.getApellido());
    }
}
