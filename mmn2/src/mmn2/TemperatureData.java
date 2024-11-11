package mmn2;
public class TemperatureData {
    private int year;
    private double[] monthlyTemperatures;

    public TemperatureData(int year, double[] monthlyTemperatures) {
        this.year = year;
        this.monthlyTemperatures = monthlyTemperatures;
    }

    public int getYear() {
        return year;
    }

    public double[] getMonthlyTemperatures() {
        return monthlyTemperatures;
    }
}