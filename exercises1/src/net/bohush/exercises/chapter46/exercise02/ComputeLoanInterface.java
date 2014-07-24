package net.bohush.exercises.chapter46.exercise02;

import java.rmi.*;

public interface ComputeLoanInterface extends Remote {
    
    public double getMonthlyPayment(double annualInterestRate, int numberOfYears, double loanAmount) throws RemoteException;
    
    public double getTotalPayment(double annualInterestRate, int numberOfYears, double loanAmount) throws RemoteException;
}
