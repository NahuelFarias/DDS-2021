package domain.models.entities.publicaciones;

import domain.models.entities.personas.Persona;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;


public class PublicacionIntencionAdoptar extends PublicacionGenerica{
    List<RespuestaSobrePregunta> preferenciasYcomodidades;
    Persona adoptante;
    Scheduler scheduler;

    public List<RespuestaSobrePregunta> getPreferenciasYcomodidades() {
        return preferenciasYcomodidades;
    }

    public void setPreferenciasYcomodidades(List<RespuestaSobrePregunta> preferenciasYcomodidades) {
        this.preferenciasYcomodidades = preferenciasYcomodidades;
    }

    public Persona getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Persona adoptante) {
        this.adoptante = adoptante;
    }

    public void inicializarScheduler(){
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDetail job = newJob(MatchearPublicacionesEnAdopcion.class)
                    .withIdentity("job", "group")
                    //.usingJobData("adoptante", adoptante)
                    //.usingJobData("preferenciasYComodidades", )
                    .build();

            int diaDeLaSemana = Calendar.getInstance().DAY_OF_WEEK;
            int horaDelDia = Calendar.getInstance().HOUR_OF_DAY;
            String periodicidad = "0 0 " +horaDelDia + " ? * " + diaDeLaSemana;

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
