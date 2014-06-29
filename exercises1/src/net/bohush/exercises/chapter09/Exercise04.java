package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise04 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String string = input.next();
		System.out.print("Enter char: ");
		char charToCount = input.next().charAt(0);
		System.out.println(count(string, charToCount));
	}
	
	public static int count(String str, char a) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == a) {
				count++;
			}
		}
		return count;
	}

}
