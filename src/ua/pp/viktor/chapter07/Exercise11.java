package ua.pp.viktor.chapter07;

import java.util.Scanner;

public class Exercise11 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a number between 0 and 511: ");
		int number = input.nextInt();
		input.close();
		final int SIZE = 9;
		int[] coins = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			coins[SIZE - i - 1] = number & 1;
			number = number >> 1;
		}
		for (int i = 0; i < SIZE; i++) {
			System.out.print((coins[i] == 0 ? "H" : "T") + ((i + 1) % 3 == 0 ? "\n" : " "));
		}
	}

}
