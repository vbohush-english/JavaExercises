package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise17 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter characters: ");
		String value = input.nextLine();
		System.out.print("Enter char: ");
		char findChar = input.next().charAt(0);
		System.out.print(count(value.toCharArray(), findChar));
	}

	public static int count(char[] chars, char a) {
		return count(chars, a, chars.length - 1);
	}
	
	public static int count(char[] chars, char a, int high) {
		if (high == 0) {
			if (chars[0] == a) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (chars[high] == a) {
				return 1 + count(chars, a, high - 1);
			} else {
				return 0 + count(chars, a, high - 1);
			}
		}	
	}

}
