package weblog;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import weblog.SessionizingReducer.TextArrayWritable;

public class SessionizingReducer extends Reducer<Text, TextPair, TextPair, TextArrayWritable >

{




public static class TextArrayWritable extends ArrayWritable {

    public TextArrayWritable(TextWritable[] values) {
        super(TextWritable.class, values);
        
    }

    @Override
    public TextWritable[] get() {
        return (TextWritable[]) super.get();
    }

    @Override
    public String toString()
    {
    	StringBuilder sb = new StringBuilder (values[0]);
    	for (int i = 1; i < values.length(); i++)
    	{
    		sb.append(" ").
    		append(values[i]);
    	}
    	
    	return sb.toString();
    }
    
   
}
}