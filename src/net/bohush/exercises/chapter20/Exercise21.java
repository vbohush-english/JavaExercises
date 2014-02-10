package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise21 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number: ");
		int value = input.nextInt();
		System.out.print(decimalToBinary(value));
	}

	public static String decimalToBinary(int value) {
		return decimalToBinary(value, 0);
	}

	public static String decimalToBinary(int value, int i) {
		int tmp = value >> i;
		if (tmp != 0) {
			return decimalToBinary(value, ++i) + (tmp & 1);
		} else {
			return "";
		}
	}

}
