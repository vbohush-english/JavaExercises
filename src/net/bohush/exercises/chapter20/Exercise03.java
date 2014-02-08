package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise03 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number1: ");
		int number1 = input.nextInt();
		System.out.print("Enter number2: ");
		int number2 = input.nextInt();
		System.out.print("gcd = " + gcd(number1, number2));
	}

	private static long gcd(long m, long n) {
		m = Math.abs(m);
		n = Math.abs(n);
		if (m % n == 0) {
			return n;
		} else {
			return gcd(n, m % n);
		}
	}

}
