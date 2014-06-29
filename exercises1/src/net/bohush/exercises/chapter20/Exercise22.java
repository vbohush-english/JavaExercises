package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise22 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number: ");
		int value = input.nextInt();
		System.out.print(decimalToHex(value));
	}

	public static String decimalToHex(int value) {
		if (value != 0) {
			if ((value % 16) < 10) {
				return decimalToHex(value / 16) + value % 16;	
			} else {
				return decimalToHex(value / 16) + (char)('A' + (value % 16 - 10));
			}
		} else {
			return "";
		}
	}

}
