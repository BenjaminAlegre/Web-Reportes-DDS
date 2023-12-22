package Cron;


import lombok.Getter;
import model.entities.ranking.Rankeador;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;

@Getter
public class EjecutorTareasCandelarizadas {

    private SchedulerFactory crearScheduler = new StdSchedulerFactory();
    private Scheduler scheduler;

    {
        try {
            scheduler = crearScheduler.getScheduler();
        } catch (SchedulerException e) {
            System.out.print("Error al crear scheduler");
        }
    }

    JobDetail job = JobBuilder.newJob(NotificadorPorHorario.class)
            .withIdentity("enviarNotificacionPorHorario")
            .build();

    Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("trigger")// Iniciar desde la medianoche
            .withSchedule(CronScheduleBuilder.cronSchedule("0 0/15 * 1/1 * ? *"))
            .build();

    public void enviarNotificacionesPorHorario() {
        try {

            scheduler.scheduleJob(job, trigger);
            scheduler.start();

        } catch (SchedulerException e) {
            System.out.print("Error al ejecutar scheduler");

        }
    }

    JobDetail job2 = JobBuilder.newJob(Rankeador.class)
            .withIdentity("generarRankings")
            .build();

    Trigger trigger2 = TriggerBuilder.newTrigger()
            .withIdentity("trigger2")
            .withSchedule(CronScheduleBuilder.cronSchedule("0 58 23 ? * SUN"))
            .build();

    public void generarRankings() {
        try {

            scheduler.scheduleJob(job2, trigger2);
            scheduler.start();
            System.out.println("salio de rankings");
        } catch (SchedulerException e) {
            System.out.print("Error al ejecutar scheduler");
            e.printStackTrace();
        }
    }


    JobDetail job3 = JobBuilder.newJob(Rankeador.class)
            .withIdentity("saludar")
            .build();

    Trigger trigger3 = TriggerBuilder.newTrigger()
            .withIdentity("trigger3")
            .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
            .build();

    public void prueba() {
        try {
            scheduler.scheduleJob(job3, trigger3);
            scheduler.start();
        } catch (SchedulerException e) {
            System.out.print("Error al ejecutar scheduler");
            e.printStackTrace();
        }
    }

    private class Prueba implements Job{

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("hola desde cron");
        }

    }
}

