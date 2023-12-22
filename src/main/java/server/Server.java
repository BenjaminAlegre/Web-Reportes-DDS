package server;


import Cron.EjecutorTareasCandelarizadas;
import org.apache.log4j.BasicConfigurator;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import spark.Spark;
import spark.debug.DebugScreen;


public class Server {

	public static void main(String[] args) throws Exception {

		//BasicConfigurator.configure();

		Spark.port(3000);
		Router.init();
		DebugScreen.enableDebugScreen();
		EjecutorTareasCandelarizadas cron = new EjecutorTareasCandelarizadas();
		EjecutorTareasCandelarizadas cron2 = new EjecutorTareasCandelarizadas();
		cron.enviarNotificacionesPorHorario();
		cron2.generarRankings();


	}
}
