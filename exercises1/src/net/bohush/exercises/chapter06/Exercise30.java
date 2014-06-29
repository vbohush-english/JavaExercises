package net.bohush.exercises.chapter06;

import java.util.Scanner;

public class Exercise30 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter list1: ");
		int listLength = input.nextInt();
		int[] list = new int[listLength];
		for (int i = 0; i < list.length; i++) {
			list[i] = input.nextInt();
		}
		input.close();
		System.out.println(isConsecutiveFour(list));
	}
	
	public static boolean isConsecutiveFour(int[] values) {
		for (int i = 0; i < values.length - 3; i++) {
			if ((values[i] == values[i + 1]) && (values[i + 1] == values[i + 2]) && (values[i + 2] == values[i + 3])) {
				return true;
			}
		}
		return false;
	}

}
