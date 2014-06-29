package net.bohush.exercises.chapter02;

import java.util.Scanner;

public class Exercise21 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter investment amount: ");
		double amount = input.nextDouble();
		System.out.print("Enter annual interest rate in percentage: ");
		double rate = input.nextDouble();
		System.out.print("Enter number of years: ");
		double years = input.nextDouble();
		
		input.close();
		
		double futureInvestmentValue = amount * Math.pow(1 + (rate / 100.0)/12, years * 12);
		
		System.out.println("Accumulated value is " + (int)(futureInvestmentValue * 100) / 100.0);
	}

}
