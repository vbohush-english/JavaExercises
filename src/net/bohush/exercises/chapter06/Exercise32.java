package net.bohush.exercises.chapter06;

import java.util.Scanner;

public class Exercise32 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter list1: ");
		int listLength = input.nextInt();
		int[] list = new int[listLength];
		for (int i = 0; i < list.length; i++) {
			list[i] = input.nextInt();
		}
		input.close();
		System.out.print(partition(list));
		System.out.print("After the partition, the list is ");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
	}
	
	public static int partition(int[] list) {
		int result = 0;
		int pivot = list[0];
		for (int i = 1; i < list.length; i++) {
			if (list[i] < pivot) {
				result++;
			}
		}
		list[0] = list[result];
		list[result] = pivot;
		

		for (int i = 0; i < result; i++) {
			if (list[i] > pivot) {
				for (int j = result + 1; j < list.length; j++) {
					if (list[j] < pivot) {
						int tmp = list[i];
						list[i] = list[j];
						list[j] = tmp;
					}
				}
			}
		}
		
		
		
		return result;
	}

}
