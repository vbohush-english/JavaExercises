package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise38 {

	public static void main(String[] args) {
		System.out.print("Enter the decimal integer: ");
		Scanner input = new Scanner(System.in);
		int integer = input.nextInt();
		input.close();
		String hesInteger = "";
		while (integer > 0) {
			if ((integer % 16) < 10) {
				hesInteger = integer % 16 + hesInteger;	
			} else {
				hesInteger = (char)('A' + (integer % 16 - 10)) + hesInteger;
			}
			integer = integer / 16;
		}
		System.out.println("The hex value is " + hesInteger);
	}

}
