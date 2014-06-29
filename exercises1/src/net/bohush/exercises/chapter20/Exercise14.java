package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise14 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter characters: ");
		String value = input.next();
		System.out.print(NumberOfUpper(value));
	}

	public static int NumberOfUpper(String value) {
		return NumberOfUpper(value, value.length() - 1);
	}
	
	public static int NumberOfUpper(String value, int high) {
		if (high == 0) {
			if (Character.isUpperCase(value.charAt(0))) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (Character.isUpperCase(value.charAt(high))) {
				return 1 + NumberOfUpper(value, high - 1);
			} else {
				return NumberOfUpper(value, high - 1);
			}
		}		
	}

}
