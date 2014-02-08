package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise04 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number: ");
		int number = input.nextInt();
		System.out.print("sum = " + sum(number));
	}

	private static double sum(int number) {
		if (number == 1) {
			return 1;
		} else {
			return sum(number - 1) + 1.0 / number;
		}
	}

}
