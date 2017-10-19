package by.barbarossa.entity;

public class Watering {
    private String periodicity;
    private int waterNorm;

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public int getWaterNorm() {
        return waterNorm;
    }

    public void setWaterNorm(int waterNorm) {
        this.waterNorm = waterNorm;
    }
}
