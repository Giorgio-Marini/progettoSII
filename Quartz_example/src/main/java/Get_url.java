import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.MalformedInputException;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class Get_url implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException{
		try{
			URL url = new URL("http://www.uniroma2.it");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			int code  = connection.getResponseCode();
			if(code == HttpURLConnection.HTTP_OK){
				BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = read.readLine();
				String html ="";
				while(line!=null){
					html += line;
					line = read.readLine();
				}
			}
			System.out.println("status connection :: " + code+ " date:: " + new Date());
		}catch(MalformedInputException ex){
			ex.printStackTrace();
		}catch (UnknownHostException ex) {
			ex.printStackTrace();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
