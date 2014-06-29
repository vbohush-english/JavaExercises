package net.bohush.exercises.chapter06;

import java.util.Scanner;

public class Exercise15 {

	public static void main(String[] args) {
		final int COUNT_OF_NUMBERS = 10;
		int[] numbers = new int[COUNT_OF_NUMBERS];
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter ten numbers: ");
		
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = input.nextInt();
		}
		input.close();
		
		System.out.print("The distinct numbers are: ");
		int[] distinctNumbers = eliminateDuplicates(numbers);
		for (int i = 0; i < distinctNumbers.length; i++) {
			System.out.print(distinctNumbers[i] + " ");
		}
		
	}
	
	public static int[] eliminateDuplicates(int[] list) {
		int count = 0;
		int[] tmparray = new int[list.length];
		for (int i = 0; i < list.length; i++) {
			int tmp = list[i];
			if (!isInArray(tmp, tmparray)) {
				tmparray[count++] = tmp;
			}
		}
		int[] result = new int[count];
		System.arraycopy(tmparray, 0, result, 0, count);
		return result;
	}
	
	public static boolean isInArray(int number, int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == number) {
				return true;
			}
		}
		return false;
	}

}
