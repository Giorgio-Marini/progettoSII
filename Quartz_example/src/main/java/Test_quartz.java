import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Test_quartz{

	public static void main(String[] args) throws Exception{
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("inserisci FREQUENZA");
//		String frequenza = bufferedReader.readLine();
//		
//		System.out.println("inserisci AM - PM");
//		String AM = bufferedReader.readLine();
//		
//		System.out.println("frequenza: " + frequenza + " AM-PM: " + AM );
		urlDependency urlD = new urlDependency();
		FileReader readerTimining = new FileReader("timing.txt");
		Scanner scannerin = new Scanner(readerTimining);
		scannerin.nextLine();
		while(scannerin.hasNextLine()){
			String timing = scannerin.nextLine();
			urlD.setValue(timing);
		}
		
		// TODO Auto-generated method stub
		JobDetail job = JobBuilder.newJob(Get_url.class).withIdentity("1", "group1").build();
		//JobDetail job2 = JobBuilder.newJob(work.class).withIdentity("2", "group2").build();
		//JobDetail job3 = JobBuilder.newJob(work.class).withIdentity("3", "group3").build();
		
		//instance Trigger, we define the time interval
//		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group1")
//				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
//						.withIntervalInSeconds(3).repeatForever()).build();
//		Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2")
//				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
//						.withIntervalInSeconds(4).repeatForever()).build();
//		//Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger3", "group3")
//				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
//						.withIntervalInSeconds(5).repeatForever()).build();
		Trigger trigger =TriggerBuilder.newTrigger().withIdentity("cronT", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule(urlD.getCronExpression()))
				.build();
		
		//now define schedulescheduler.scheduleJob(job,trigger);
		
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job,trigger);
	}

}
