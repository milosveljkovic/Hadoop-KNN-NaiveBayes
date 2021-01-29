import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceClass extends Reducer<Text, DistanceTarget, Text, IntWritable>{


    protected void reduce(Text key, Iterable<DistanceTarget> values, Context context)
            throws IOException, InterruptedException {
        int k = 5;
        int class1=0, class0=0;
        TreeMap<Double, Integer> currKnnMap = new TreeMap<Double, Integer>();
        for(DistanceTarget val: values){
            int target = val.getTarget().get();
            double distance = val.getDistance().get();
            currKnnMap.put(distance, target);
        }
        for(int i=0; i<k; i++) {
            int target = currKnnMap.pollFirstEntry().getValue();
            if(target == 1){ //has value
                class1++;
            }else {
                class0++;
            }
        }

        if(class1 > class0){
            context.write(key, new IntWritable(1));
        }else {
            context.write(key, new IntWritable(0));
        }

    }
}
