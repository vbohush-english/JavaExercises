package net.bohush.exercises.chapter08;

import java.util.Date;

public class Exercise07 {

	public static void main(String[] args) {
		Account account = new Account(1122, 20000);
		Account.setAnnualInterestRate(4.5);
		account.withdraw(2500);
		account.deposit(3000);
		System.out.println("Balance = " + account.getBalance());
		System.out.println("Monthly interest = " + account.getMonthlyInterest());
		System.out.println("Created = " + account.getDateCreated());
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
