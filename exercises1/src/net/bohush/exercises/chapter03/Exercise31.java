package net.bohush.exercises.chapter03;

import java.util.Scanner;

public class Exercise31 {

	public static void main(String[] args) {
		System.out.print("Enter the exchange rate from dollars to RMB: ");
		Scanner input = new Scanner(System.in);
		double exchangeRate = input.nextDouble();
		System.out.print("Enter 0 to convert dollars to RMB and 1 vice versa: ");
		int convertType = input.nextInt();
		if (convertType == 0) {
			System.out.print("Enter the dollar amount: ");
			double dollarAmount = input.nextDouble();
			double rmbAmount = dollarAmount * exchangeRate;
			System.out.print("$" + (int)(dollarAmount * 100) / 100.0 + " is " + (int)(rmbAmount * 100) / 100.0 + " yuan");
		} else if (convertType == 1) {
			System.out.print("Enter the RMB amount: ");
			double rmbAmount = input.nextDouble();
			double dollarAmount =  rmbAmount / exchangeRate;
			System.out.print((int)(rmbAmount * 100) / 100.0 + " yuan is $" + (int)(dollarAmount * 100) / 100.0);
		} else {
			System.out.println("Incorrect input");
		}
		input.close();
	}

}
