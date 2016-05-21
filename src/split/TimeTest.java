package split;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeTest
{

	public static void main(String[] args)
	{
		String workingDir = System.getProperty("user.dir");
		System.out.println(workingDir);
		String inFile = workingDir + File.separator+ "data" +File.separator + "2015_07_22_mktplace_shop_web_log_sample.log";
		
		File input = new File (inFile);
		
		String delim = "\" \"|\" | \"|\"";
		
		String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		
		//Duration myDuration = 
				
		try
		{
			FileReader ifs = new FileReader(inFile);
			BufferedReader bf = new BufferedReader(ifs);
			
			int counter = 0;
			String line;
			while ((line = bf.readLine()) != null && counter <100)
			{
				String[] tokens = line.split(delim);
				System.out.println("String "+ (counter +1));
				
				String ip = tokens[0].split(" ")[2].split(":")[0];
				String time = tokens[0].split(" ")[0];
				String url = tokens[1].split(" ")[1];
				System.out.println(ip + " " + time + " " + url);
				
				LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
				
				Duration myDuration = dateTime.

	}

}
