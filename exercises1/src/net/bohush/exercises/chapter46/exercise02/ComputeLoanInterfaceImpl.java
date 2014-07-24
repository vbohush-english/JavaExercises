package net.bohush.exercises.chapter46.exercise02;

import java.rmi.*;
import java.rmi.server.*;

public class ComputeLoanInterfaceImpl extends UnicastRemoteObject implements ComputeLoanInterface {

    public ComputeLoanInterfaceImpl() throws RemoteException {
    }

    @Override
    public double getMonthlyPayment(double annualInterestRate, int numberOfYears, double loanAmount) throws RemoteException {
        double monthlyInterestRate = annualInterestRate / 1200;
        return loanAmount * monthlyInterestRate/ (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
    }

    @Override
    public double getTotalPayment(double annualInterestRate, int numberOfYears, double loanAmount) throws RemoteException {
        return getMonthlyPayment(annualInterestRate, numberOfYears, loanAmount) * numberOfYears * 12;
    }
        

}
