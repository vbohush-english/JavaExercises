package ua.pp.viktor.chapter02;

import java.util.Scanner;

public class Exercise06 {

	public static void main(String[] args) {
		System.out.print("Enter a number between 0 and 1000: ");
		Scanner input = new Scanner(System.in);
		int number = input.nextInt();
		input.close();
		int sumOfDigits = number / 100;
		sumOfDigits += (number % 100) / 10;
		sumOfDigits += number % 10;
		System.out.println("The sum of the digits is " + sumOfDigits);
	}

}
