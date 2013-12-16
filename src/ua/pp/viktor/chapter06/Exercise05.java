package ua.pp.viktor.chapter06;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise05 {

	public static void main(String[] args) {
		final int COUNT_OF_NUMBERS = 10;
		boolean[] numbers = new boolean[COUNT_OF_NUMBERS];
		Arrays.fill(numbers, false);
		Scanner input = new Scanner(System.in);
		System.out.print("Enter ten numbers: ");
		for (int i = 0; i < COUNT_OF_NUMBERS; i++) {
			numbers[input.nextInt()] = true;			
		}
		input.close();
		System.out.print("The distinct numbers are: ");
		for (int i = 0; i < COUNT_OF_NUMBERS; i++) {
			if (numbers[i]) {
				System.out.print(i + " ");
			}
		}
		
	}

}
