package domain.models.entities.publicaciones;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.models.entities.mascotas.Organizacion;
import domain.models.entities.personas.Persona;

import java.util.Calendar;
import java.util.List;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;


public class PublicacionIntencionAdoptar extends PublicacionGenerica{
    List<RespuestaSobrePregunta> preferenciasYcomodidades;
    Cuestionario cuestionarioPreferencias;
    Cuestionario cuestionarioComodidades;
    Persona adoptante;
    Scheduler scheduler;
    private Organizacion organizacion;

    @Override
    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Cuestionario getCuestionarioPreferencias() {
        return cuestionarioPreferencias;
    }

    public void setCuestionarioPreferencias(Cuestionario cuestionarioPreferencias) {
        this.cuestionarioPreferencias = cuestionarioPreferencias;
    }

    public Cuestionario getCuestionarioComodidades() {
        return cuestionarioComodidades;
    }

    public void setCuestionarioComodidades(Cuestionario cuestionarioComodidades) {
        this.cuestionarioComodidades = cuestionarioComodidades;
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
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonAdoptante = gson.toJson(adoptante);
           String jsonPreferenciasYComodidades = gson.toJson(preferenciasYcomodidades);

            JobDetail job = newJob(MatchearPublicacionesEnAdopcion.class)
                    .withIdentity("job", "group")
                    .usingJobData("adoptante", jsonAdoptante)
                    .usingJobData("preferenciasYComodidades", jsonPreferenciasYComodidades)
                    .build();

            int diaDeLaSemana = Calendar.getInstance().DAY_OF_WEEK;
            int horaDelDia = Calendar.getInstance().HOUR_OF_DAY;
            String periodicidad = "0 0 " + horaDelDia + " ? * " + diaDeLaSemana;

            CronTrigger trigger = newTrigger()
                    .withIdentity("trigger", "group")
                    .withSchedule(cronSchedule(periodicidad))
                    .build();

            scheduler.scheduleJob(job, trigger);
        }   catch (SchedulerException se) {
            se.printStackTrace();
        }
    }


}
