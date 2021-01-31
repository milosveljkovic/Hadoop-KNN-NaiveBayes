import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class NaiveBayes extends Configured implements Tool{

    public static void main(String[] args) throws Exception{
        int exitCode = ToolRunner.run(new NaiveBayes(), args);
        System.exit(exitCode);
    }

    public int run(String[] args) throws Exception {

        //PREPROCESSING
        Job job = new Job();
        job.setJarByClass(NaiveBayes.class);

        job.setJobName("preProcessing");
        FileInputFormat.addInputPath(job, new Path(args[0]));   //NB/training
        FileOutputFormat.setOutputPath(job, new Path(args[1])); //NB/cache


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DoubleWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapperClass(MapClass.class);
        job.setReducerClass(ReduceClass.class);
        job.waitForCompletion(true);
        //PREPROCESSING
        //FREQUENCY TABLE
        Job job1 = new Job();
        job1.setJarByClass(NaiveBayes.class);

        job1.setJobName("frequencyTable");
        FileInputFormat.addInputPath(job1, new Path(args[0]));   //NB/training
        FileOutputFormat.setOutputPath(job1, new Path(args[2])); //NB/frequencyTable


        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(IntWritable.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(DoubleWritable.class);
        job1.setOutputFormatClass(TextOutputFormat.class);

        job1.setMapperClass(MapClassFT.class);
        job1.setReducerClass(ReduceClassFT.class);
        job1.waitForCompletion(true);
        //FREQUENCY TABLE
        //FIT MODEL
        Job job2 = new Job();
        job2.setJarByClass(NaiveBayes.class);

        job2.setJobName("fitModel");
        FileInputFormat.addInputPath(job2, new Path(args[3]));   //NB/test
        FileOutputFormat.setOutputPath(job2, new Path(args[4])); //NB/output


        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(Estimation.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(IntWritable.class); //INT WRITABLE
        job2.setOutputFormatClass(TextOutputFormat.class);

        job2.setMapperClass(MapClassFIT.class);
        job2.setReducerClass(ReduceClassFIT.class);
        //FIT MODEL
        int returnValue = job2.waitForCompletion(true) ? 0:1;

        if(job2.isSuccessful()) {
            System.out.println("successful");
        } else if(!job2.isSuccessful()) {
            System.out.println("unsuccessful");
        }
        return returnValue;
    }
}