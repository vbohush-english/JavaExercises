package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise16 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number: ");
		int number = input.nextInt();
		input.close();
		for (int i = 2; i <= number; i++) {
			if (number % i == 0) {
				System.out.print(i + " ");
				number /= i;
				i = 1;
			}
		}
	}

}
