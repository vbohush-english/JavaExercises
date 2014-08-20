package net.bohush.exercises.other;

public class HourlyWageEmployee extends Employee{

    private double hourlyRate;
    
    public HourlyWageEmployee(String name, int id, double hourlyRate) {
        super(name, id);
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double getAverageMonthlySalary() {
        return 20.8 * 8 * hourlyRate;
    }
    
    @Override
    public String getData() {
        return super.getData() + "\t" + hourlyRate;
    }    
}
