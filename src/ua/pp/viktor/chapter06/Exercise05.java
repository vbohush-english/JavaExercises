package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise05 {

	public static void main(String[] args) {
		final int COUNT_OF_NUMBERS = 10;
		
		int[] numbers = new int[COUNT_OF_NUMBERS];
		int count = 0;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter ten numbers: ");
		
		for (int i = 0; i < COUNT_OF_NUMBERS; i++) {
			int tmp = input.nextInt();
			if (!isInArray(tmp, numbers)) {
				numbers[count++] = tmp;
			}
		}
		input.close();
		
		System.out.print("The distinct numbers are: ");
		for (int i = 0; i < count; i++) {
			System.out.print(numbers[i] + " ");
		}
		
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
