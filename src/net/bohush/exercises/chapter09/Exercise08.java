package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise08 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter binary string: ");
		String string = input.next();
		System.out.print(binaryToDecimal(string));
	}

	public static int binaryToDecimal(String binaryString) {
		int result = 0;
		int twoPow = 1;
		for (int i = binaryString.length() - 1; i >= 0; i--) {
			if (binaryString.charAt(i) != '0') {
				result += twoPow;
			}
			twoPow *= 2;
		}
		return result;
	}
}
