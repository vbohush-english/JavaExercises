package net.bohush.exercises.chapter06;

import java.util.Scanner;
import java.util.Arrays;

public class Exercise03 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		final int NUMBER_OF_INTEGERS  = 100;
		int[] integers = new int[NUMBER_OF_INTEGERS];
		Arrays.fill(integers, 0);
		System.out.print("Enter the integers between 1 and 100: ");
		int nextInteger = input.nextInt();
		while (nextInteger != 0) {
			integers[nextInteger]++;
			nextInteger = input.nextInt();
		}
		input.close();
		for (int i = 0; i < NUMBER_OF_INTEGERS; i++) {
			if (integers[i] != 0) {
				System.out.println(i + " occurs " + integers[i] + " time" + (integers[i] > 1 ? "s" : ""));
			}
		}
	}

}
