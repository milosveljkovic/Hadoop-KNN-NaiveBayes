import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class ReduceClassFT extends Reducer<Text, IntWritable, Text, DoubleWritable> {

    protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        if(key.toString().equals("class1") || key.toString().equals("class1") ){
            int value = 0;
            for(IntWritable val: values){
                value = val.get();
            }
            context.write(key, new DoubleWritable(value));
        }else {
            int sum = 0;
            for(IntWritable val: values){
                int value = val.get();
                sum += value;
            }
            context.write(key, new DoubleWritable(sum));
        }
    }
}
