package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise08 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number: ");
		int number = input.nextInt();
		reverseDisplay(number);
	}

	public static void reverseDisplay(int number) {
		if (number < 10) {
			System.out.print(number + "");
		} else {
			System.out.print(number % 10 + "");
			reverseDisplay(number / 10);
		}
	}

}
