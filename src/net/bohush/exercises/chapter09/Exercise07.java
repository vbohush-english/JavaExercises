package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise07 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String string = input.next();
		for (int i = 0; i < string.length(); i++) {
			int tmp = getNumber(string.charAt(i));
			if (tmp == -1) {
				System.out.print(string.charAt(i));
			} else {
				System.out.print(tmp);
			}
		}

	}

	public static int getNumber(char uppercaseLetter) {
		uppercaseLetter = Character.toLowerCase(uppercaseLetter);
		if (Character.isDigit(uppercaseLetter)) {
			return Character.getNumericValue(uppercaseLetter);
		} else if(Character.isAlphabetic(uppercaseLetter)) {
			if (uppercaseLetter < 's') {
				return 2 + (uppercaseLetter - 'a') / 3;
			} else {
				if (uppercaseLetter == 's') {
					return 7;
				} else if ((uppercaseLetter == 't') || (uppercaseLetter == 'u') || (uppercaseLetter == 'v')) {
					return 8;
				} else {
					return 9;
				}
			}
		} else {
			return -1;
		}
	}
}
