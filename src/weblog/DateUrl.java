package weblog;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class DateUrl implements WritableComparable<DateUrl> {

	private Text mDate;
	private Text mUrl;
	
	public DateUrl()
	{
		set (new Text(), new Text());
	}
	
	public DateUrl (String date, String url)
	{
		set (new Text (date), new Text(url));
	}
	
	
	public DateUrl (Text date, Text url)
	{
		set(date, url);
	}
	
	public void set (Text date, Text url)
	{
		mDate = date;
		mUrl = url;
	}
	
	
	public Text getDate() {
		return mDate;
	}

	public Text getUrl() {
		return mUrl;
	}
	
	

	@Override
	public void readFields(DataInput in) throws IOException 
	{
		mDate.readFields(in);
		mUrl.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException 
	{
		mDate.write(out);
		mUrl.write(out);
	}

	@Override
	public int compareTo(DateUrl pArg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString()
	{
		return mDate + " " + mUrl;
	}
	
	

}
