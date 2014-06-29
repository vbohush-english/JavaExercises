package net.bohush.exercises.chapter20;

import java.util.Scanner;

public class Exercise13 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter 8 nymbers: ");
		int[] values = new int[8];
		for (int i = 0; i < 8; i++) {
			values[i] = input.nextInt();
		}		
		
		System.out.println(max(values));
	}

	public static int max(int[] values) {
		return max(values, values.length - 1);
	}
	
	public static int max(int[] values, int high) {
		if (high == 0) {
			return values[0];
		} else {
			int max2 =  max(values, high - 1);
			if (values[high] > max2) {
				return values[high];
			} else {
				return max2;
			}
		}
	}

}
