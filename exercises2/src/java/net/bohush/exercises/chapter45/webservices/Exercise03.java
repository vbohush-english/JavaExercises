package net.bohush.exercises.chapter45.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "Exercise03", serviceName = "Exercise45_03")
public class Exercise03 {
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private java.util.Date loanDate;
    
    public Exercise03() {
        this(10, 1, 1000);
    }

    public Exercise03(double annualInterestRate, int numberOfYears, double loanAmount) {
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        loanDate = new java.util.Date();
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    @WebMethod(operationName = "getMonthlyPayment")
    public double getMonthlyPayment() {
        System.out.print(toString());
        double monthlyInterestRate = annualInterestRate / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 - (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
        return monthlyPayment;
    }

    @WebMethod(operationName = "getTotalPayment")
    public double getTotalPayment() {
        double totalPayment = getMonthlyPayment() * numberOfYears * 12;
        return totalPayment;
    }

    public java.util.Date getLoanDate() {
        return loanDate;
    }

    @Override
    public String toString() {
        return "[annualInterestRate = " + annualInterestRate + ", numberOfYears = " + numberOfYears + ", loanAmount = " + loanAmount + "]";
    }
    
}