package domain.models.entities.publicaciones;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.personas.Contacto;
import domain.models.entities.personas.Persona;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;


public class PublicacionIntencionAdopcion extends PublicacionGenerica{
    private Cuestionario cuestionarioPreferenciasYComodidades;
    private Persona adoptante;
    private Scheduler scheduler;
    private Organizacion organizacion;


    @Override
    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Cuestionario getCuestionarioPreferenciasYComodidades() {
        return cuestionarioPreferenciasYComodidades;
    }

    public void setCuestionarioPreferenciasYComodidades(Cuestionario cuestionarioPreferenciasYComodidades) {
        this.cuestionarioPreferenciasYComodidades = cuestionarioPreferenciasYComodidades;
    }

//    public List<RespuestaSobrePregunta> getPreferenciasYcomodidades() { return preferenciasYcomodidades; }

//    public void setPreferenciasYcomodidades(List<RespuestaSobrePregunta> preferenciasYcomodidades) { this.preferenciasYcomodidades = preferenciasYcomodidades; }



    public Persona getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Persona adoptante) {
        this.adoptante = adoptante;
    }

    public void inicializarScheduler(){
        try {
            SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
            scheduler.start();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            //TODO: Errores
            //String jsonAdoptante = gson.toJson(adoptante);
            //String jsonPreferenciasYComodidades = gson.toJson(cuestionarioPreferenciasYComodidades);
           //String jsonContactos = gson.toJson(adoptante.getContactos());

            ArrayList<String> preguntas = obtenerListaDePreguntas();
            ArrayList<String> respuestas = obtenerListaDeRespuestas();

            JobDetail job = newJob(MatchearPublicacionesEnAdopcion.class)
                    .withIdentity("job", "group")
                    //.usingJobData("adoptante", jsonAdoptante)
                    //.usingJobData("preferenciasYComodidades", jsonPreferenciasYComodidades)
                    .usingJobData("preguntas", gson.toJson(preguntas))
                    .usingJobData("respuestas", gson.toJson(respuestas))
                    .build();

            int diaDeLaSemana = Calendar.getInstance().DAY_OF_WEEK;
            int horaDelDia = Calendar.getInstance().HOUR_OF_DAY;
            String periodicidad = "0 0 " + horaDelDia + " ? * " + diaDeLaSemana;

//            CronTrigger trigger = newTrigger()
//                    .withIdentity("trigger", "group")
//                    .withSchedule(cronSchedule(periodicidad))
//                    .build();

            //Trigger a usar durante los test
            SimpleTrigger trigger = newTrigger()
                    .withIdentity("trigger", "group")
                    .withSchedule(SimpleScheduleBuilder.repeatMinutelyForTotalCount(1))
                    .build();

            scheduler.scheduleJob(job, trigger);
        }   catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public ArrayList<String> obtenerListaDePreguntas(){

        ArrayList<String> preguntas = new ArrayList<>();
        cuestionarioPreferenciasYComodidades.getRespuestas().forEach(respuestaSobrePregunta ->
            preguntas.add(respuestaSobrePregunta.getPregunta().getPregunta())
                );

        return preguntas;
    }

    public ArrayList<String> obtenerListaDeRespuestas(){

        ArrayList<String> respuestas = new ArrayList<>();
        cuestionarioPreferenciasYComodidades.getRespuestas().stream().forEach(respuestaSobrePregunta ->
                respuestas.add(respuestaSobrePregunta.getRespuesta())
        );

        return respuestas;
    }


}
