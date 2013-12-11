package ua.pp.viktor.chapter04;

import java.util.Scanner;

public class Exercise31 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the initial deposit amount: ");
		double amount = input.nextDouble();
		System.out.print("Enter annual percentage yield: ");
		double annualPercentageYield = input.nextDouble();
		System.out.print("Enter maturity period (number of months): ");
		int months = input.nextInt();
		input.close();
		
		double value = amount;
		System.out.println("Month\tCD Value");
		for (int i = 1; i <= months; i++) {
			value += value * annualPercentageYield / 1200;
			System.out.printf("%d\t%.2f\n", i, value);
		}
	}

}
