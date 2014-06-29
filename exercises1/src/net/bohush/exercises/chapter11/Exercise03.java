package net.bohush.exercises.chapter11;

import java.util.Date;

public class Exercise03 {

	public static void main(String[] args) {

	}

}

class SavingsAccount extends Account {
	@Override
	public String toString() {
		return "SavingsAccount";
	}
}

class CheckingAccount extends Account {
	private double overdraftLimit;
	
	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	public CheckingAccount() {
		super();
		overdraftLimit = 10000.0;
	}
	
	public CheckingAccount(int id, double balance, double overdraftLimit) {
		super(id, balance);
		this.overdraftLimit = overdraftLimit;
	}
	

	@Override
	public String toString() {
		return "CheckingAccount";
	}
}

class Account {
	private int id;
	private double balance;
	private static double annualInterestRate;
	private Date dateCreated = new Date();
	
	Account() {		
	}
	
	Account(int id, double balance) {
		this.id = id;
		this.balance = balance;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public static void setAnnualInterestRate(double annualInterestRate) {
		Account.annualInterestRate = annualInterestRate;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public double getMonthlyInterestRate() {
		return (annualInterestRate / 100) / 12;
	}
	
	public double getMonthlyInterest() {
		return balance * getMonthlyInterestRate();
	}
	
	public void withdraw(double amount) {
		if ((amount > 0) && (amount <= balance)) {
			balance -= amount;
		}
	}
	
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
		}
	}
	
}
