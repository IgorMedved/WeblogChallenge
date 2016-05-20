package weblog;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;

public class WebLogChallenge {

	public static void main(String[] args) throws Exception {
		
		if (args.length!=2)
			System.err.println ("Usage: WebLogChalleange <input path> <output path>");

		
		//Define MapReduce job
		Configuration conf = new Configuration();
	    Job preOrderJob = Job.getInstance(conf, "weblog challenge");
	    preOrderJob.setJarByClass(WebLogChallenge.class);
	    preOrderJob.setMapperClass(SessionizingMapper.class);
	   // job.setCombinerClass(IntSumReducer.class);
	    preOrderJob.setReducerClass(IntSumReducer.class);
	    preOrderJob.setOutputKeyClass(Text.class);
	    preOrderJob.setOutputValueClass(IntWritable.class);
	    FileInputFormat.addInputPath(preOrderJob, new Path(args[0]));
	    FileOutputFormat.setOutputPath(preOrderJob, new Path(args[1]));
	    System.exit(preOrderJob.waitForCompletion(true) ? 0 : 1);
	}

}