package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise30 {

	public static void main(String[] args) {
		System.out.print("Enter a string: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		System.out.print("The new string is: " + title(s));
	}

	
	public static String title(String s) {
		char[] charArray = s.toCharArray();
		if (Character.isLowerCase(charArray[0])) {
			charArray[0] = Character.toUpperCase(charArray[0]);
		}
		for (int i = 0; i < charArray.length - 1; i++) {
			if ((charArray[i] == ' ') && (Character.isLowerCase(charArray[i + 1]))) {
				charArray[i + 1] = Character.toUpperCase(charArray[i + 1]);
			}
		}
		
		return new String(charArray);
	}
}
