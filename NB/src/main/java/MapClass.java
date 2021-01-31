import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapClass extends Mapper<LongWritable, Text, Text, DoubleWritable>{

    @Override
     public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] data = line.split(",");
        for (int i = 0; i < 10; i++) {
            if(i == 0){
                contextWrite("age,"+data[13].trim(), Double.parseDouble(data[0]), context);
            } else if (i == 3) {
                contextWrite("trestbps,"+data[13].trim(), Double.parseDouble(data[3]), context);
            } else if (i == 4) {
                contextWrite("chol,"+data[13].trim(), Double.parseDouble(data[4]), context);
            } else if (i == 7) {
                contextWrite("thalach,"+data[13].trim(), Double.parseDouble(data[7]), context);
            } else if (i == 9) {
                contextWrite("oldpeak,"+data[13].trim(), Double.parseDouble(data[9]), context);
            }
        }
    }

    public void contextWrite(String key, Double value, Context context){
        try {
            Text outputKey = new Text();
            outputKey.set(key);
            context.write(outputKey, new DoubleWritable(Double.valueOf(value)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}