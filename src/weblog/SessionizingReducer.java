package weblog;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import weblog.SessionizingReducer.TextArrayWritable;

public class SessionizingReducer extends Reducer<Text, TextPair, TextPair, TextArrayWritable>
{
	public static final long TIME_WINDOW = (long) (10E6*60*15); // 15 MIN
	
	@Override
	public void reduce(Text key, Iterable<TextPair> values, Context context)
	{
		String  dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		
		Iterator<TextPair> value = values.iterator();
		LocalDateTime sessionStart=null;

		
		
		

		long startTimeInMillis;
		long lastTimeInMillis;
		TextPair tempPair = value.hasNext()? value.next(): null;
		
		if (tempPair!=null)
		{
			List<Text> urls = new ArrayList<>();

			sessionStart = LocalDateTime.parse(tempPair.getDate().toString(), formatter);
			urls.add(value.next().getUrl());
		
		
			LocalDateTime lastTime =  sessionStart;
			LocalDateTime currentTime;
			Duration clickTime;
			

			while (value.hasNext())
			{
				tempPair = value.next();
				currentTime = LocalDateTime.parse(tempPair.getDate().toString(), formatter);
				clickTime = Duration.between(lastTime, currentTime);
				
				if (clickTime.toNanos()/1000 < TIME_WINDOW)
				{
					lastTime = currentTime;
					urls.add(tempPair.getUrl());
				}
				else // write down old session initiate new one
				{
					
					
				}
				
				
			
			}
		}
		
	}
	
	public String format (Duration sessionTime)
	{
		
		Duration temp;
		long days = sessionTime.toDays();
		temp = sessionTime.minusDays(days);
		long hours = temp.toHours();
		temp = temp.minusHours(hours);
		long minutes = temp.toMinutes();
		temp = temp.minusMinutes(minutes);
		long seconds = temp.getSeconds();
		temp = temp.minusSeconds(seconds);
		long micros = temp.getNano()/1000;
		
		
		
		return String.format(arg0, arg1);
	}
	
	

	public static class TextArrayWritable extends ArrayWritable
	{

		public TextArrayWritable(Text[] values)
		{
			super(Text.class, values);
		}

		@Override
		public Text[] get()
		{
			return (Text[]) super.get();
		}

		@Override
		public String toString()
		{
			Text[] values = get();

			StringBuilder sb = new StringBuilder(values[0].toString());
			for (int i = 1; i < values.length; i++)
			{
				sb.append(" ").append(values[i]);
			}

			return sb.toString();
		}

	}
}