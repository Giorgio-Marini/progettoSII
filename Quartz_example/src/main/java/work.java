import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class work implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException{
		String jobname = context.getJobDetail().getKey().getName();
		String jobgroup = context.getJobDetail().getKey().getGroup();
		
		System.out.println("job name = "+ jobname+" jobGroup = "+ jobgroup+" now: "+ new Date());
	}
}
