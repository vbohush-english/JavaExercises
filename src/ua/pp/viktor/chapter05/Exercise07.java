package ua.pp.viktor.chapter05;

import java.util.Scanner;

public class Exercise07 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter investment amount: ");
		double amount = input.nextDouble();
		System.out.print("Enter annual interest rate in percentage: ");
		double rate = input.nextDouble();
		input.close();
		
		System.out.println("Years\tFuture Value");
		for (int i = 1; i <= 30; i++) {
			System.out.printf("%d\t%10.2f\n", i, futureInvestmentValue(amount, rate, i));
		}
	}
	
	public static double futureInvestmentValue(double investmentAmount, double monthlyInterestRate, int years) {
		return investmentAmount * Math.pow(1 + (monthlyInterestRate / 100.0)/12, years * 12);
	}

}
