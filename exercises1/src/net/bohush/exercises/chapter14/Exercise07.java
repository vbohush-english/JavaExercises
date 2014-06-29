package net.bohush.exercises.chapter14;

import java.util.Scanner;

public class Exercise07 {

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
			if (binaryString.charAt(i) == '1') {
				result += twoPow;
			} else if (binaryString.charAt(i) != '0') {
				throw new NumberFormatException();
			}
			twoPow *= 2;
		}
		return result;
	}
}
