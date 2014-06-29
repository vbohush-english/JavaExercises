package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise31 {

	public static void main(String[] args) {
		System.out.print("Enter a string: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		System.out.print("The new string is: " + swapCase(s));
	}

	
	public static String swapCase(String s) {
		char[] charArray = s.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (Character.isLowerCase(charArray[i])) {
				charArray[i] = Character.toUpperCase(charArray[i]);
			} else if (Character.isUpperCase(charArray[i])) {
				charArray[i] = Character.toLowerCase(charArray[i]);
			}
		}		
		return new String(charArray);
	}
}
