package weblog;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UrlReducer extends Reducer <Text, Iterable<Text>, Text, Iterable<Text>>
{
	public void reduce(Text key, Iterable<Text> values, Context context)
	{
		Iterator<Text> iterator = values.iterator();
		Text value = null;
		if (iterator.hasNext())
		{
			value = iterator.next();
			
		}
		
		while (iterator.hasNext())
		{
			Text temp = iterator.next();
			if (temp.toString().equals(value.toString()))
			{
				iterator.remove();
			}
			else
			{
				value = temp;
			}
		}
		
		try
		{
			context.write(key, values);
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
