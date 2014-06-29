package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise23 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number: ");
		String binaryString = input.next();
		System.out.println(binaryToDecimal(binaryString));
	}
	
	public static int binaryToDecimal(String binaryString) {
		return binaryToDecimal(binaryString, 1);
	}

	public static int binaryToDecimal(String binaryString, int step) {
		if (binaryString.length() == 0) {
			return 0;
		} else {
			return (binaryString.charAt(binaryString.length() - 1) == '1' ? step : 0) + binaryToDecimal(binaryString.substring(0, binaryString.length() - 1), step * 2);
		}
	}

}
