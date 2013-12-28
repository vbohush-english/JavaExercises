package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise29 {

	public static void main(String[] args) {
		System.out.print("Enter the first 12 digits of an ISBN-13: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String isbn = input.next();
		int sum = 0;
		for (int i = 0; i < isbn.length(); i++) {
			if (i % 2 == 0) {
				sum += Character.getNumericValue(isbn.charAt(i));
			} else {
				sum += 3 * Character.getNumericValue(isbn.charAt(i));
			}
		}
		sum = (10 - sum % 10) % 10;
		System.out.println("The ISBN-13 number is " + isbn + sum);
	}

}
