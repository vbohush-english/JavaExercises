package net.bohush.exercises.chapter05;

import java.util.Scanner;

public class Exercise04 {

	public static void main(String[] args) {
		System.out.print("Enter an integer: ");
		Scanner input = new Scanner(System.in);
		int integer = input.nextInt();
		input.close();
		System.out.print("Reverse of " + integer + " is ");
		reverse(integer);
	}
	
	public static void reverse(int number) {
		int result = 0;
		while (number > 0) {
			result = result * 10 + (number % 10);
			number /= 10;
		}
		System.out.print(result);
	}

}
