package split;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class App {

	public static void main(String[] args) 
	{
		String workingDir = System.getProperty("user.dir");
		System.out.println(workingDir);
		String inFile = workingDir + File.separator+ "data" +File.separator + "2015_07_22_mktplace_shop_web_log_sample.log";
		
		File input = new File (inFile);
		
		//String line = "Hello \"Beauty aaa\" \"Where did you go\"";
		String delim = "\" \"|\" | \"|\"";
		
		String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		//LocalTime test = new LocalTime(555555555l);
		
		//DateFormat format = new SimpleDateFormat(dateFormat);
		
		//System.out.println(format.toString());
		
		
		
		
		
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
				//ParsePosition pos = new ParsePosition(0);
				//Date date = format.parse(time, pos);
				
				if (dateTime == null)
					System.out.println("Error in line " + counter);
				else
					System.out.println(dateTime);
				/*for (String token: tokens)
				{
					System.out.println(token);
				}*/
				int insideCounter = 0;
				/*while (sTkn.hasMoreTokens())
				{
					
					String tkn = sTkn.nextToken().trim();
					/*if (tkn.length() !=0)
						System.out.println(tkn);*/
/*					if (insideCounter ==1 && tkn.length()<30)
						System.out.println("String "+ (counter +1) + "\n" +tkn);
					insideCounter ++;
				}
				//System.out.println(bf.readLine());*/
				counter ++;
			}
			
			bf.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(inFile + " is not a valid file");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
