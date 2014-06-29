package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise26 {

	public static void main(String[] args) {
		System.out.print("Enter the first 9 digits of an ISBN: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String isbn = input.next();
		int sum = 0;
		for (int i = 0; i < isbn.length(); i++) {
			sum += (i + 1) * Character.getNumericValue(isbn.charAt(i));
		}
		sum %= 11;
		System.out.print("The ISBN-10 number is " + isbn + (sum == 10 ? "X" : sum));
	}

}
