import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DistanceTarget implements WritableComparable<DistanceTarget> {

    private DoubleWritable distance;
    private IntWritable target;

    public DistanceTarget(){
        set(new DoubleWritable(), new IntWritable());
    }

    public DistanceTarget(DoubleWritable distance, IntWritable target){
        set(distance, target);
    }

    public void set(DoubleWritable distance, IntWritable target){
        this.distance = distance;
        this.target = target;
    }

    public IntWritable getTarget(){
        return target;
    }

    public DoubleWritable getDistance(){
        return distance;
    }

    @Override
    public int compareTo(DistanceTarget o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        distance.write(dataOutput);
        target.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        distance.readFields(dataInput);
        target.readFields(dataInput);
    }
}
