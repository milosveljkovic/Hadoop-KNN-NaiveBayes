import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RecordsArray {
    public ArrayList<Record> records;

    public RecordsArray() {
        records = new ArrayList<Record>();
    }

    public void populate(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        try {
            while((line = reader.readLine()) != null){
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
                records.add(new Record( age,  sex,  cp,  trestbps,
                        chol,  fbs,  restecg,  thalach,  exang,
                        oldpeak, slope,  ca,  thal,  target));
            }
        }catch (NumberFormatException e){

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
