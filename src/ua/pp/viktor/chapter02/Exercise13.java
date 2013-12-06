package ua.pp.viktor.chapter02;

import java.util.Scanner;

public class Exercise13 {

	public static void main(String[] args) {
		System.out.print("Enter the monthly saving amount: ");
		Scanner input = new Scanner(System.in);
		double amount = input.nextDouble();
		input.close();
		double value = amount * (1 + 0.00417);
		value = (amount + value) * (1 + 0.00417);
		value = (amount + value) * (1 + 0.00417);
		value = (amount + value) * (1 + 0.00417);
		value = (amount + value) * (1 + 0.00417);
		value = (amount + value) * (1 + 0.00417);
		System.out.println("After the sixth month, the account value is $" + (int)(100 * value)/100.0);
	}

}
