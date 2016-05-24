package weblog;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaximumTimeMapper extends Mapper<Object, Text, Text, LongWritable>
{
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, LongWritable>.Context context)
	{
		String line = value.toString();
		
		String[] tokens = line.split(" ");
		
		String ip = tokens[0];
		String[] time = tokens[1].split(":");
		long days = Long.parseLong(time[0]);
		long hours = Long.parseLong(time[1]);
		long minutes = Long.parseLong(time[2]);
		String[] secondMicros = time[3].split(".");
		long seconds = Long.parseLong(secondMicros[0]);
		long nanos = Long.parseLong(secondMicros[1])*1000;
		
		
		nanos =  ((((days*24 + hours) * 60 + minutes)*60 +seconds)*1000000000 + nanos);
		
		try
		{
			context.write(new Text(ip), new LongWritable(nanos));
		} catch (IOException | InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
