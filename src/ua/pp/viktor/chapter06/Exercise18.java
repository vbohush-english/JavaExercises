package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise18 {

	public static void main(String[] args) {
		System.out.print("Enter list: ");
		Scanner input = new Scanner(System.in);
		int[] list = new int[10];
		for (int i = 0; i < list.length; i++) {
			list[i] = input.nextInt();
		}
		bubbleSort(list);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		input.close();
	}
	
	public static void bubbleSort(int[] list) {
		for(int i = 0; i < list.length - 1; i++) {
			for(int j = 0; j < list.length - 1; j++) {
				if (list[j] > list[j + 1]) {
					int tmp = list[j];
					list[j] = list [j + 1];
					list[j + 1] = tmp;
				}
			}
		}
	}

}
