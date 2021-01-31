import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.ArrayList;

public class ReduceClass extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{

    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context)
            throws IOException, InterruptedException {
        double mean = 0, stddev = 0, sum = 0, stddevSum = 0;
        ArrayList<Double> curValues = new ArrayList<>();
        int counter = 0;
        for(DoubleWritable val: values){
            double value = val.get();
            curValues.add(value);
            sum += value;
            counter++;
        }
        mean = sum / counter ;
        for(Double val: curValues) {
            double value = val.doubleValue();
            double squareAddtions = Math.pow((value - mean), 2);
            stddevSum += squareAddtions;
        }
        stddev = Math.pow( stddevSum/(counter-1), 0.5);
        Text outputKeyMean = new Text(key+",mean");
        Text outputKeyStddev = new Text(key+",stddev");
        context.write(outputKeyMean, new DoubleWritable(mean));
        context.write(outputKeyStddev, new DoubleWritable(stddev));
    }
}
