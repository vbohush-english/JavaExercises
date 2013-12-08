package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise26 {

	public static void main(String[] args) {
		System.out.print("Enter an integer: ");
		Scanner input = new Scanner(System.in);
		int number = input.nextInt();
		input.close();
		System.out.println("Is " + number + " divisible by 5 and 6? " + ((number % 5 == 0) && (number % 6 == 0)));
		System.out.println("Is " + number + " divisible by 5 or 6? " + ((number % 5 == 0) || (number % 6 == 0)));
		System.out.println("Is " + number + " divisible by 5 or 6, but not both? " + ((number % 5 == 0) ^ (number % 6 == 0)));
	}
}
