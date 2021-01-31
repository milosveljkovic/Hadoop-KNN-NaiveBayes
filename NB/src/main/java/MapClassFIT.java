import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

public class MapClassFIT extends Mapper<LongWritable, Text, Text, Estimation>{

    private HashMap<String, Double> preProcessingTable, frequencyTable;

    @Override
    protected void setup(Mapper.Context context) throws IOException, InterruptedException {
        BufferedReader cacheReader = new BufferedReader(new FileReader("./NB/cache/part-r-00000"));
        BufferedReader ftReader = new BufferedReader(new FileReader("./NB/frequencyTable/part-r-00000"));
        preProcessingTable = new HashMap<>();
        frequencyTable = new HashMap<>();
        String line;
        try {
            while((line = cacheReader.readLine()) != null) {
                String[] data = line.split("\t");
                preProcessingTable.put(data[0], Double.parseDouble(data[1]));
            }

            while((line = ftReader.readLine()) != null) {
                String[] data = line.split("\t");
                frequencyTable.put(data[0], Double.parseDouble(data[1]));
            }
        }catch (NumberFormatException e){

        } finally {
            if (cacheReader != null || ftReader != null) {
                try {
                    cacheReader.close();
                    ftReader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] data = line.split(",");
        double age1,age0,sex1,sex0,cp1,cp0,trestbps1,trestbps0,chol1,chol0,fbs1,fbs0,restecg1,restecg0,thalach1,thalach0,exang1,exang0,oldpeak1,oldpeak0,slope1,slope0,ca1,ca0,thal1,thal0;
        age1=age0=sex1=sex0=cp1=cp0=trestbps1=trestbps0=chol1=chol0=fbs1=fbs0=restecg1=restecg0=thalach1=thalach0=exang1=exang0=oldpeak1=oldpeak0=slope1=slope0=ca1=ca0=thal1=thal0=0;
        for (int i = 0; i < 14; i++) {
            if( i == 0){
                age1 = normalDistribution(Double.parseDouble(data[0]), preProcessingTable.get("age,1,mean"), preProcessingTable.get("age,1,stddev"));
                age0 = normalDistribution(Double.parseDouble(data[0]), preProcessingTable.get("age,0,mean"), preProcessingTable.get("age,0,stddev"));
            } else if(i == 1){
                sex1 = frequencyTable.get("sex,1,"+data[1].trim())/frequencyTable.get("class1");
                sex0 = frequencyTable.get("sex,0,"+data[1].trim())/frequencyTable.get("class0");
            } else if (i == 2) {
                cp1 = frequencyTable.get("cp,1,"+data[2].trim())/frequencyTable.get("class1");
                cp0 = frequencyTable.get("cp,0,"+data[2].trim())/frequencyTable.get("class0");
            } else if (i == 3) {
                trestbps1 = normalDistribution(Double.parseDouble(data[3]), preProcessingTable.get("trestbps,1,mean"), preProcessingTable.get("trestbps,1,stddev"));
                trestbps0 = normalDistribution(Double.parseDouble(data[3]), preProcessingTable.get("trestbps,0,mean"), preProcessingTable.get("trestbps,0,stddev"));
            } else if (i == 4) {
                chol1 = normalDistribution(Double.parseDouble(data[4]), preProcessingTable.get("chol,1,mean"), preProcessingTable.get("chol,1,stddev"));
                chol0 = normalDistribution(Double.parseDouble(data[4]), preProcessingTable.get("chol,0,mean"), preProcessingTable.get("chol,0,stddev"));
            } else if (i == 5) {
                fbs1 = frequencyTable.get("fbs,1,"+data[5].trim())/frequencyTable.get("class1");
                fbs0 = frequencyTable.get("fbs,0,"+data[5].trim())/frequencyTable.get("class0");
            } else if (i == 6) {
                restecg1 = frequencyTable.get("restecg,1,"+data[6].trim())/frequencyTable.get("class1");
                restecg0 = frequencyTable.get("restecg,0,"+data[6].trim())/frequencyTable.get("class0");
            } else if (i == 7) {
                thalach1 = normalDistribution(Double.parseDouble(data[7]), preProcessingTable.get("thalach,1,mean"), preProcessingTable.get("thalach,1,stddev"));
                thalach0 = normalDistribution(Double.parseDouble(data[7]), preProcessingTable.get("thalach,0,mean"), preProcessingTable.get("thalach,0,stddev"));
            }  else if (i == 8) {
                exang1 = frequencyTable.get("exang,1,"+data[8].trim())/frequencyTable.get("class1");
                exang0 = frequencyTable.get("exang,0,"+data[8].trim())/frequencyTable.get("class0");
            } else if (i == 9) {
                oldpeak1 = normalDistribution(Double.parseDouble(data[9]), preProcessingTable.get("oldpeak,1,mean"), preProcessingTable.get("oldpeak,1,stddev"));
                oldpeak0 = normalDistribution(Double.parseDouble(data[9]), preProcessingTable.get("oldpeak,0,mean"), preProcessingTable.get("oldpeak,0,stddev"));
            } else if (i == 10) {
                slope1 = frequencyTable.get("slope,1,"+data[10].trim())/frequencyTable.get("class1");
                slope0 = frequencyTable.get("slope,0,"+data[10].trim())/frequencyTable.get("class0");
            } else if (i == 11) {
                ca1 = frequencyTable.get("ca,1,"+data[11].trim())/frequencyTable.get("class1");
                ca0 = frequencyTable.get("ca,0,"+data[11].trim())/frequencyTable.get("class0");
            } else if (i == 12) {
                thal1 = frequencyTable.get("thal,1,"+data[12].trim())/frequencyTable.get("class1");
                thal0 = frequencyTable.get("thal,0,"+data[12].trim())/frequencyTable.get("class0");
            }
        }
        DecimalFormat df = new DecimalFormat("#.#####");
        double total1 = frequencyTable.get("class1") / (frequencyTable.get("class1")+frequencyTable.get("class0"));
        double total0 = frequencyTable.get("class0") / (frequencyTable.get("class1")+frequencyTable.get("class0"));
        double class1 = age1*sex1*cp1*trestbps1*chol1*fbs1*restecg1*thalach1*exang1*oldpeak1*slope1*ca1*thal1*total1;
        double class0 = age0*sex0*cp0*trestbps0*chol0*fbs0*restecg0*thalach0*exang0*oldpeak0*slope0*ca0*thal0*total0;
        Estimation e = new Estimation(new DoubleWritable(class1), new DoubleWritable(class0));
        context.write(value, e);
    }

    public double normalDistribution(Double value, Double mean, Double stddev){
        return (1/(Math.sqrt(2*Math.PI)*stddev))*(Math.pow(Math.E,(-(Math.pow(value-mean, 2))/(2*Math.pow(stddev, 2)))));
    }
}