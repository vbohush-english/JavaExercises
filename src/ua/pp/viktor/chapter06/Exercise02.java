package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise02 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		final int NUMBER_OF_INTEGERS = 10;
		System.out.print("Enter " + NUMBER_OF_INTEGERS + " integers: ");		
		int[] integers = new int[NUMBER_OF_INTEGERS];
		for (int i = 0; i < NUMBER_OF_INTEGERS; i++) {
			integers[i] = input.nextInt();
		}
		input.close();
		for (int i = NUMBER_OF_INTEGERS - 1; i >= 0; i--) {
			System.out.print(integers[i] + " ");
		}
	}

}
