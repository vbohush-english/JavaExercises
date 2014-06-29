package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise37 {

	public static void main(String[] args) {
		System.out.print("Enter the decimal integer: ");
		Scanner input = new Scanner(System.in);
		int integer = input.nextInt();
		input.close();
		String binaryInteger = "";
		while (integer > 0) {
			binaryInteger = integer % 2 + binaryInteger;
			integer = integer / 2;
		}
		System.out.println("The binary value is " + binaryInteger);
	}

}
