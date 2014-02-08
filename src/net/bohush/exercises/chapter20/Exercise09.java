package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter characters: ");
		String value = input.next();
		reverseDisplay(value);
	}

	public static void reverseDisplay(String value) {
		if (value.length() == 1) {
			System.out.print(value);
		} else {
			System.out.print(value.charAt(value.length() - 1));
			reverseDisplay(value.substring(0, value.length() - 1));
		}
	}

}
