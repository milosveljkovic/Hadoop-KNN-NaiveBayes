import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Estimation implements WritableComparable<Estimation> {

    private DoubleWritable class1;
    private DoubleWritable class0;

    public Estimation(){
        set(new DoubleWritable(), new DoubleWritable());
    }

    public Estimation(DoubleWritable class1, DoubleWritable class0){
        set(class1, class0);
    }

    public void set(DoubleWritable class1, DoubleWritable class0) {
        this.class1 = class1;
        this.class0 = class0;
    }

    public DoubleWritable getClass0(){
        return class0;
    }

    public DoubleWritable getClass1(){
        return class1;
    }

    @Override
    public int compareTo(Estimation o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        class1.write(dataOutput);
        class0.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        class1.readFields(dataInput);
        class0.readFields(dataInput);
    }
}
