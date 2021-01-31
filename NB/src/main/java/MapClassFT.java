import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class MapClassFT extends Mapper<LongWritable, Text, Text, IntWritable>{

    private int class1 = 0, class0 = 0;

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] data = line.split(",");
        int target = Integer.parseInt(data[13]);
        if(target == 1) {
            class1++;
        } else {
            class0++;
        }
        for (int i = 0; i < 14; i++) {
            if(i == 1){
                contextWrite("sex,"+data[13].trim()+","+data[1].trim(), 1, context);
            } else if (i == 2) {
                contextWrite("cp,"+data[13].trim()+","+data[2].trim(), 1, context);
            } else if (i == 5) {
                contextWrite("fbs,"+data[13].trim()+","+data[5].trim(), 1, context);
            } else if (i == 6) {
                contextWrite("restecg,"+data[13].trim()+","+data[6].trim(), 1, context);
            } else if (i == 8) {
                contextWrite("exang,"+data[13].trim()+","+data[8].trim(), 1, context);
            } else if (i == 10) {
                contextWrite("slope,"+data[13].trim()+","+data[10].trim(), 1, context);
            } else if (i == 11) {
                contextWrite("ca,"+data[13].trim()+","+data[11].trim(), 1, context);
            } else if (i == 12) {
                contextWrite("thal,"+data[13].trim()+","+data[12].trim(), 1, context);
            }
        }
    }

    public void contextWrite(String key, Integer value, Context context){
        try {
            Text outputKey = new Text();
            outputKey.set(key);
            context.write(outputKey, new IntWritable(value));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void cleanup(Mapper.Context context) throws IOException, InterruptedException {
        contextWrite("class1", class1, context);
        contextWrite("class0", class0, context);
    }
}