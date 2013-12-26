package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise10 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number: ");
		int nember = input.nextInt();
		System.out.print(decimalToBinary(nember));
	}

	public static String decimalToBinary(int value) {
		String binaryInteger = "";
		while (value > 0) {
			binaryInteger = value % 2 + binaryInteger;
			value = value / 2;
		}
		return binaryInteger;
	}
}
