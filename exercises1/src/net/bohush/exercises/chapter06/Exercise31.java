package net.bohush.exercises.chapter06;

import java.util.Scanner;

public class Exercise31 {

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
		System.out.print("The merged list is: ");
		int[] mergedList = merge(list1, list2);
		for (int i = 0; i < mergedList.length; i++) {
			System.out.print(mergedList[i] + " ");
		}
	}

	public static int[] merge(int[] list1, int[] list2) {
		int[] result = new int[list1.length + list2.length];
		int list1pos = 0;
		int list2pos = 0;
		for (int i = 0; i < result.length; i++) {
			if (list1pos >= list1.length) {
				result[i] = list2[list2pos++];
			} else if (list2pos >= list2.length) {
				result[i] = list1[list1pos++];
			} else if (list1[list1pos] < list2[list2pos]){
				result[i] = list1[list1pos++];
			} else {
				result[i] = list2[list2pos++];
			}
		}
		return result;
	}
}
