package net.bohush.exercises.chapter02;

import java.util.Scanner;

public class Exercise05 {

	public static void main(String[] args) {
		System.out.print("Enter the subtotal and a gratuity rate: ");
		Scanner input = new Scanner(System.in);
		double subtotal = input.nextDouble();
		double gratuityRate = input.nextDouble();
		input.close();
		double gratuity = gratuityRate / 100 * subtotal;
		double total = subtotal + gratuity;
		System.out.println("The gratuity is $" + gratuity + " and total is $" + total);
	}

}
