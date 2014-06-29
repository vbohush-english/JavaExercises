package net.bohush.exercises.chapter11;

import java.util.Date;
import java.util.ArrayList;


public class Exercise08 {

	public static void main(String[] args) {
		Account2 a = new Account2("George", 1122, 1000);
		Account2.setAnnualInterestRate(1.5);
		a.deposit(30);
		a.deposit(40);
		a.deposit(50);
		a.withdraw(5);
		a.withdraw(4);
		a.withdraw(2);
		System.out.println("Account holder name: " + a.getName());
		System.out.println("Interest rate: " + a.getMonthlyInterestRate());
		System.out.println("Balance: " + a.getBalance());
		System.out.println("Transactions: ");
		ArrayList<Transaction> t = a.getTransactions();
		for (int i = 0; i < t.size(); i++) {
			System.out.println(t.get(i));
		}
	}

}

class Transaction {
	private Date date = new Date();
	private char type;
	private double amount;
	private double balance;
	private String description;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	Transaction(char type, double amount, double balance, String description) {
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return date + "\t" + type + "\t" + amount + "\t" + balance + "\t" +  description;
	}
}

class Account2 {
	
	private String name; 
	private int id;
	private double balance;
	private static double annualInterestRate;
	private Date dateCreated = new Date();
	private ArrayList<Transaction> transactions = new ArrayList<>();
	
	Account2() {
	}
	
	Account2(String name, int id, double balance) {
		this.name = name;
		this.id = id;
		this.balance = balance;	
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
		Account2.annualInterestRate = annualInterestRate;
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
			transactions.add(new Transaction('W', amount, balance, "withdraw $" + amount));			
		}
	}
	
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			transactions.add(new Transaction('D', amount, balance, "deposit $" + amount));
		}
	}
	
}
