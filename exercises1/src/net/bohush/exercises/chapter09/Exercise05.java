package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise05 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String string = input.next();
		int[] numbers = count(string);
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(i + " - " + numbers[i]);
		}

	}

	
	public static int[] count(String s) {
		int[] result = new int[10];
		for (int i = 0; i < 10; i++) {
			result[i] = count(s, (char)('0' + i));
		}
		return result;		
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
