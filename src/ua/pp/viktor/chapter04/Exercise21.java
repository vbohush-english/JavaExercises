package ua.pp.viktor.chapter04;

import java.util.Scanner;
public class Exercise21 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Loan Amount: ");
		double amount = input.nextDouble();
		System.out.print("Number of Years: ");
		double years = input.nextDouble();
		input.close();
		System.out.println("Interest Rate\tMonthly Payment\tTotal Payment\n");
		for (double rate = 5.0; rate <= 8.0; rate += 0.125) {
			double monthlyInterestRate = rate / 1200;
			double monthlyPayment = amount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, years * 12));
			double totalPayment = monthlyPayment * years * 12;
			System.out.printf("%.3f%%\t\t%.2f\t\t%.2f\n", rate, monthlyPayment, totalPayment);
		}

	}

}
