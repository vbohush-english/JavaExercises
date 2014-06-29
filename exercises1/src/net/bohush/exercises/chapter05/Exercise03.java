package net.bohush.exercises.chapter05;

import java.util.Scanner;

public class Exercise03 {

	public static void main(String[] args) {
		System.out.print("Enter an integer: ");
		Scanner input = new Scanner(System.in);
		int integer = input.nextInt();
		input.close();
		System.out.println("Reverse of " + integer + " is " + reverse(integer) + ", it is" + (isPalindrome(integer) ? "" : " not") + " a palindrome.");

	}
	
	public static int reverse(int number) {
		int result = 0;
		while (number > 0) {
			result = result * 10 + (number % 10);
			number /= 10;
		}
		return result;
	}

	public static boolean isPalindrome(int number) {
		return number == reverse(number);
	}

}
