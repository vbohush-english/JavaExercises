package net.bohush.exercises.chapter06;

import java.util.Scanner;

public class Exercise27 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter list1: ");		
		int list1Length = input.nextInt();
		int[] list1 = new int[list1Length];
		for (int i = 0; i < list1.length; i++) {
			list1[i] = input.nextInt();
		}
		System.out.print("Enter list2: ");		
		int list2Length = input.nextInt();
		int[] list2 = new int[list2Length];
		for (int i = 0; i < list2.length; i++) {
			list2[i] = input.nextInt();
		}
		input.close();
		System.out.println("Two lists are " + (equals(list1, list2) ? "" : "not ") + "identical");
	}

	public static boolean equals(int[] list1, int[] list2) {
		Exercise18.bubbleSort(list1);
		Exercise18.bubbleSort(list2);
		return Exercise26.equals(list1, list2);
	}
}
