package weblog;

import java.io.IOException;
import java.time.Duration;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageTimeReducer extends Reducer<Text, Iterable<LongWritable>, Text, Text>
{
	public void reduce(Text key, Iterable<LongWritable> values, Context context)
	{
		long counter = 0;
		double sum = 0;
		for (LongWritable value: values)
		{
			counter++;
			sum += (double)value.get();
		}
		

		Duration average = Duration.ofNanos((long)(sum/counter));
		try
		{
			context.write(key, new Text(Utils.format(average)));
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
