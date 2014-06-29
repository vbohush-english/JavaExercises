package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise16 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter characters: ");
		String value = input.nextLine();
		System.out.print(NumberOfUpper(value.toCharArray()));
	}

	public static int NumberOfUpper(char[] chars) {
		return NumberOfUpper(chars, chars.length - 1);
	}
	
	public static int NumberOfUpper(char[] chars, int high) {
		if (high == 0) {
			if (Character.isUpperCase(chars[0])) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (Character.isUpperCase(chars[high])) {
				return 1 + NumberOfUpper(chars, high - 1);
			} else {
				return NumberOfUpper(chars, high - 1);
			}
		}		
	}

}
