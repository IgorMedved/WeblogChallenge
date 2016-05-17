package tokenizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

// test StringTokenizer
public class App
{

	public static void main(String[] args) 
	{
		String workingDir = System.getProperty("user.dir");
		System.out.println(workingDir);
		String inFile = workingDir + File.separator+ "data" +File.separator + "2015_07_22_mktplace_shop_web_log_sample.log";
		System.out.println(inFile);
		
		File input = new File (inFile);
		
		try
		{
			FileReader ifs = new FileReader(inFile);
			BufferedReader bf = new BufferedReader(ifs);
			
			int counter = 0;
			String line;
			while ((line = bf.readLine()) != null )
			{
				//String line = bf.readLine();
				StringTokenizer sTkn = new StringTokenizer(line, "\"");
				//System.out.println("String "+ (counter +1));
				int insideCounter = 0;
				while (sTkn.hasMoreTokens())
				{
					
					String tkn = sTkn.nextToken().trim();
					/*if (tkn.length() !=0)
						System.out.println(tkn);*/
					if (insideCounter ==1 && tkn.length()<30)
						System.out.println("String "+ (counter +1) + "\n" +tkn);
					insideCounter ++;
				}
				//System.out.println(bf.readLine());
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
		
		
		String test = "Hello beautiful world";
		
		StringTokenizer stringTokenizer = new StringTokenizer(test);
		while (stringTokenizer.hasMoreTokens())
		{
			System.out.println(stringTokenizer.nextToken());
		}
	}

}
