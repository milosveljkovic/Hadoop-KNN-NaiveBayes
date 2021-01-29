public class Record {
    int age;
    int sex;
    int cp; //chest_pain
    int trestbps;
    int chol;
    int fbs;
    int restecg;
    int thalach;
    int exang;
    double oldpeak;
    int slope;
    int ca;
    int thal;
    int target;

    public Record(){

    }

    public Record(int age, int sex, int cp, int trestbps, int chol, int fbs, int restecg, int thalach, int exang, double oldpeak,
                          int slope, int ca, int thal, int target)
    {
        this.age = age;
        this.sex = sex;
        this.cp = cp;
        this.trestbps = trestbps;
        this.chol = chol;
        this.fbs = fbs;
        this.restecg = restecg;
        this.thalach = thalach;
        this.exang = exang;
        this.oldpeak = oldpeak;
        this.slope = slope;
        this.ca = ca;
        this.thal = thal;
        this.target = target;
    }

    public double calculateEuclideanDistance(Record r){
        int eAge = this.age - r.age;
        int eSex = this.sex - r.sex;
        int eCp = this.cp - r.cp;
        int eTrestbps = this.trestbps - r.trestbps;
        int eChol = this.chol - r.chol;
        int eFbs = this.fbs - r.fbs;
        int eRestecg = this.restecg - r.restecg;
        int eThalach = this.thalach - r.thalach;
        int eExang = this.exang-r.exang;
        double eOldpeak = this.oldpeak - r.oldpeak;
        int eSlope = this.slope - r.slope;
        int eCa = this.ca - r.ca;
        int eThal = this.thal - r.thal;
        //int eTarget = this.target - r.target;
        double squareSum = Math.pow(eAge, 2) + Math.pow(eSex, 2)+ Math.pow(eCp, 2)
                + Math.pow(eTrestbps, 2) + Math.pow(eChol, 2) + Math.pow(eFbs, 2)
                + Math.pow(eRestecg, 2) + Math.pow(eThalach, 2) + Math.pow(eExang, 2)
                + Math.pow(eOldpeak, 2) + Math.pow(eSlope, 2) + Math.pow(eCa, 2)
                + Math.pow(eThal, 2);
        return Math.sqrt(squareSum);
    }

    //setter
    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public void setTrestbps(int trestbps) {
        this.trestbps = trestbps;
    }

    public void setChol(int chol) {
        this.chol = chol;
    }

    public void setFbs(int fbs) {
        this.fbs = fbs;
    }

    public void setRestecg(int restecg) {
        this.restecg = restecg;
    }

    public void setThalach(int thalach) {
        this.thalach = thalach;
    }

    public void setExang(int exang) {
        this.exang = exang;
    }

    public void setOldpeak(double oldpeak) {
        this.oldpeak = oldpeak;
    }

    public void setSlope(int slope) {
        this.slope = slope;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

    public void setThal(int thal) {
        this.thal = thal;
    }

    public void setTarget(int target) {
        this.target = target;
    }
    //setter

    //getter
    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex;
    }

    public int getCp() {
        return cp;
    }

    public int getTrestbps() {
        return trestbps;
    }

    public int getChol() {
        return chol;
    }

    public int getFbs() {
        return fbs;
    }

    public int getRestecg() {
        return restecg;
    }

    public int getThalach() {
        return thalach;
    }

    public int getExang() {
        return exang;
    }

    public double getOldpeak() {
        return oldpeak;
    }

    public int getSlope() {
        return slope;
    }

    public int getCa() {
        return ca;
    }

    public int getThal() {
        return thal;
    }

    public int getTarget() {
        return target;
    }
    //getter
}
