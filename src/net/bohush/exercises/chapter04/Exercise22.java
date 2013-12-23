package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise22 {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.print("Loan Amount: ");
		double amount = input.nextDouble();
		System.out.print("Number of Years: ");
		double years = input.nextDouble();
		System.out.print("Annual Interest Rate: ");
		double rate = input.nextDouble();

		input.close();

		double monthlyInterestRate = rate / 1200;
		double monthlyPayment = amount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, years * 12));
		double totalPayment = monthlyPayment * years * 12;

		System.out.printf("\nMonthly Payment: %.2f\n", monthlyPayment);
		System.out.printf("Total Payment: %.2f\n", totalPayment);
		System.out.println("\nPayment#\tInterest\tPrincipal\tBalance");

		for (int i = 0; i < years * 12; i++) {
			double interest = monthlyInterestRate * amount;
			double principal = monthlyPayment - interest;
			amount = amount - principal;
			System.out.printf("%d\t\t%.2f\t\t%.2f\t\t%.2f\n", i + 1, interest, principal, amount);
		}
	}

}
