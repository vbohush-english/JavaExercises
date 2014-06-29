package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise42 {

	public static void main(String[] args) {
		System.out.print("Enter commision sought: ");
		Scanner input = new Scanner(System.in);
		double commision = input.nextDouble();
		input.close();
		System.out.printf("Sales amount = %.2f\n", (commision - 5000 - (5000 * 0.08 + 5000 * 0.10)) / 0.12 + 10000);
	}

}
