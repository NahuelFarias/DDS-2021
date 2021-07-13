package domain.models.entities.publicaciones;

import domain.models.entities.personas.Persona;
import org.quartz.*;

import java.util.List;

public class MatchearPublicacionesEnAdopcion implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        List<RespuestaSobrePregunta> preferenciasYComodidades = (List<RespuestaSobrePregunta>) dataMap.get("preferenciasYComodidades");
        Persona adoptante = (Persona) dataMap.get("adoptante");

        //Invocar metodo en la persona para setearle una lista con las publicaciones matcheadas??

        //System.out.println(adoptante.getApellido());
    }
}
