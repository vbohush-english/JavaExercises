package net.bohush.exercises.chapter10;

import java.util.Date;
import java.util.Scanner;

public class Exercise07 {

	public static void main(String[] args) {
		Account[] accounts = new Account[10];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = new Account(0, 100);
		}
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		while (true) {
			int id = -1;
			while ((id >= 10) || (id < 0)) {
				System.out.print("\nEnter an id: ");
				id = input.nextInt();
			}
			
			boolean continueThisAccount = true;
			do {
				int choise = -1;
				while ((choise >= 5) || (choise < 1)) {
					System.out.print("\nMain menu\n1: check balance\n2: withdraw\n3: deposit\n4: exit\nEnter a choice: ");
					choise = input.nextInt();
				}
				switch (choise) {
				case 1:
					System.out.println("The balance is " + accounts[id].getBalance());
					break;
				case 2:
					System.out.print("Enter an amount to withdraw: ");
					double amount = input.nextDouble();
					accounts[id].withdraw(amount);
					break;
				case 3:
					System.out.print("Enter an amount to deposit: ");
					double deposit = input.nextDouble();
					accounts[id].deposit(deposit);
					break;
				case 4:
					continueThisAccount = false;
				}
			} while(continueThisAccount);
		}

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