package net.bohush.exercises.other;

public class FixedPaymentEmployee extends Employee{

    private double monthlyPayment;
    
    public FixedPaymentEmployee(String name, int id, double monthlyPayment) {
        super(name, id);
        this.monthlyPayment = monthlyPayment;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public double getAverageMonthlySalary() {
        return monthlyPayment;
    }
    
}
