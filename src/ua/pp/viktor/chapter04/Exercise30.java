package ua.pp.viktor.chapter04;

import java.util.Scanner;

public class Exercise30 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter an amount: ");
		double amount = input.nextDouble();
		System.out.print("Enter the annual interest rate: ");
		double annualInterestRate = input.nextDouble();
		double monthlyInterestRate = (annualInterestRate / 100) / 12; 
		System.out.print("Enter number of months: ");
		int months = input.nextInt();
		input.close();
		
		double value = 0;
		for (int i = 1; i <= months; i++) {
			value = (amount + value) * (1 + monthlyInterestRate); 
		}
		System.out.printf("Your value: %.2f", value);
	}

}
