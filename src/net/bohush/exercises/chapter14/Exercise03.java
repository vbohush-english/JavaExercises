package net.bohush.exercises.chapter14;

import java.util.Scanner;

public class Exercise03 {

	public static void main(String[] args) {
		int[] array = new int[100];

		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * Integer.MAX_VALUE);
		}

		try {
			System.out.print("Enter an index of an array: ");

			@SuppressWarnings("resource")
			int index = new Scanner(System.in).nextInt();

			System.out.print(array[index]);
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.print("Out of Bounds");
		}
	}

}
