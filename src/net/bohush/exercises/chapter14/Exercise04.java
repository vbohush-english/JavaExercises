package net.bohush.exercises.chapter14;

import java.util.Scanner;

public class Exercise04 {

	public static void main(String[] args) {
		// Create a Scanner
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		// Enter yearly interest rate
		System.out.print("Enter yearly interest rate, for example, 8.25: ");
		double annualInterestRate = input.nextDouble();

		// Enter number of years
		System.out.print("Enter number of years as an integer: ");
		int numberOfYears = input.nextInt();

		// Enter loan amount
		System.out.print("Enter loan amount, for example, 120000.95: ");
		double loanAmount = input.nextDouble();

		// Create Loan object
		Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);

		// Display loan date, monthly payment, and total payment
		System.out.printf("The loan was created on %s\n"
				+ "The monthly payment is %.2f\nThe total payment is %.2f\n",
				loan.getLoanDate().toString(), loan.getMonthlyPayment(),
				loan.getTotalPayment());
	}

}

class Loan {
	private double annualInterestRate;
	private int numberOfYears;
	private double loanAmount;
	private java.util.Date loanDate;

	/** Default constructor */
	public Loan() {
		this(2.5, 1, 1000);
	}

	/**
	 * Construct a loan with specified annual interest rate, number of years,
	 * and loan amount
	 */
	public Loan(double annualInterestRate, int numberOfYears, double loanAmount) {
		setAnnualInterestRate(annualInterestRate);
		setNumberOfYears(numberOfYears);
		setLoanAmount(loanAmount);
		loanDate = new java.util.Date();
	}

	/** Return annualInterestRate */
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	/** Set a new annualInterestRate */
	public void setAnnualInterestRate(double annualInterestRate) {
		if (annualInterestRate <= 0) {
			throw new IllegalArgumentException();
		}
		this.annualInterestRate = annualInterestRate;
	}

	/** Return numberOfYears */
	public int getNumberOfYears() {
		return numberOfYears;
	}

	/** Set a new numberOfYears */
	public void setNumberOfYears(int numberOfYears) {
		if (numberOfYears <= 0) {
			throw new IllegalArgumentException();
		}
		this.numberOfYears = numberOfYears;
	}

	/** Return loanAmount */
	public double getLoanAmount() {
		return loanAmount;
	}

	/** Set a newloanAmount */
	public void setLoanAmount(double loanAmount) {
		if (loanAmount <= 0) {
			throw new IllegalArgumentException();
		}
		this.loanAmount = loanAmount;
	}

	/** Find monthly payment */
	public double getMonthlyPayment() {
		double monthlyInterestRate = annualInterestRate / 1200;
		double monthlyPayment = loanAmount * monthlyInterestRate / (1 - (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
		return monthlyPayment;
	}

	/** Find total payment */
	public double getTotalPayment() {
		double totalPayment = getMonthlyPayment() * numberOfYears * 12;
		return totalPayment;
	}

	/** Return loan date */
	public java.util.Date getLoanDate() {
		return loanDate;
	}
}