import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class ReduceClassFIT extends Reducer<Text, Estimation, Text, IntWritable> {

    protected void reduce(Text key, Iterable<Estimation> values, Context context)
            throws IOException, InterruptedException {
        double class1, class0;
        class0 = class1 = 0;
        for(Estimation val: values){
            class1 = val.getClass1().get();
            class0 = val.getClass0().get();
        }
        if(class1 > class0) {
            context.write(key, new IntWritable(1));
        } else {
            context.write(key, new IntWritable(0));
        }
    }
}
