package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise12 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter characters: ");
		String value = input.next();
		reverseDisplay(value);
	}

	public static void reverseDisplay(String value) {
		reverseDisplay(value, value.length() - 1);
	}
	
	public static void reverseDisplay(String value, int high) {
		System.out.print(value.charAt(high));
		if (high != 0) {
			reverseDisplay(value, high - 1);
		}
	}

}
