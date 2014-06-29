package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise11 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number: ");
		long number = input.nextLong();
		System.out.print("sum = " + sumDigits(number));
	}

	public static long sumDigits(long number) {
		if (number < 10) {
			return number;
		} else {
			return number % 10 + sumDigits(number / 10);
		}
	}
}
