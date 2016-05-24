package weblog;

import java.io.IOException;
import java.time.Duration;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaximumTimeReducer extends Reducer<Text, Iterable<LongWritable>, Text, Text>
{
	public void reduce (Text key, Iterable<LongWritable> values, Context context)
	{
		long max = Long.MIN_VALUE;
		
		for (LongWritable value :values)
		{
			if (value.get() > max)
			{
				max = value.get();
			}
			
			
		}
		
		try
		{
			context.write(key, new Text(Utils.format(Duration.ofNanos(max))));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
