import java.io.File;
import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapClass extends Mapper<LongWritable, Text, Text, DistanceTarget>{

    private RecordsArray trainingSet;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        trainingSet = new RecordsArray();
        trainingSet.populate(new File("./KNN/cache/TrainingRecords.txt"));
    }

    @Override
     public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] data = line.split(",");
        int age = Integer.parseInt(data[0]);
        int sex = Integer.parseInt(data[1]);
        int cp = Integer.parseInt(data[2]);
        int trestbps = Integer.parseInt(data[3]);
        int chol = Integer.parseInt(data[4]);
        int fbs = Integer.parseInt(data[5]);
        int restecg = Integer.parseInt(data[6]);
        int thalach = Integer.parseInt(data[7]);
        int exang = Integer.parseInt(data[8]);
        double oldpeak = Double.parseDouble(data[9]);
        int slope = Integer.parseInt(data[10]);
        int ca = Integer.parseInt(data[11]);
        int thal = Integer.parseInt(data[12]);
        int target = Integer.parseInt(data[13]);

        Record testRecord = new Record( age,  sex,  cp,  trestbps,
                chol,  fbs,  restecg,  thalach,  exang,
                oldpeak, slope,  ca,  thal,  target);

        for(Record r: trainingSet.records){
            double distance = r.calculateEuclideanDistance(testRecord);
            DistanceTarget dt = new DistanceTarget(new DoubleWritable(distance), new IntWritable(r.target));
            Text outputKey = new Text();
            outputKey.set(line);
            context.write(outputKey, dt);
        }
    }
}