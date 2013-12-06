package ua.pp.viktor.chapter02;

import java.util.Scanner;

public class Exercise20 {

	public static void main(String[] args) {
		System.out.print("Enter balance and interest rate (e.g., 3 for 3%): ");
		Scanner input = new Scanner(System.in);
		double balance = input.nextDouble();
		double interestRate = input.nextDouble();
		input.close();
		double interest = balance * (interestRate / 1200);
		System.out.println("The interest is " + (int)(100 * interest) / 100.0);
	}

}
