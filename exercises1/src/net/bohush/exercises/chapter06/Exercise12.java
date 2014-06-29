package net.bohush.exercises.chapter06;

import java.util.Scanner;

public class Exercise12 {

	public static void main(String[] args) {
		System.out.print("Enter ten numbers: ");
		Scanner input = new Scanner(System.in);
		final int N = 10;
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = input.nextInt();
		}
		input.close();
		reverse(numbers);
		for (int i = 0; i < N; i++) {
			System.out.print(numbers[i] + " ");
		}
	}

	public static int[] reverse(int[] list) {
		for (int i = 0; i < list.length / 2; i++) {
			int tmp = list[i];
			list[i] = list[list.length - 1 - i];
			list[list.length - 1 - i] = tmp;
		}
		return list;
	}
}
