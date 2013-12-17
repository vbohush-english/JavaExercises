package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise20 {

	public static void main(String[] args) {
		System.out.print("Enter list: ");
		Scanner input = new Scanner(System.in);
		double[] list = new double[10];
		for (int i = 0; i < list.length; i++) {
			list[i] = input.nextDouble();
		}
		selectionSort(list);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		input.close();
	}

	public static void selectionSort(double[] list) {
		for (int i = list.length - 1; i > 0; i--) {
			double currentMax = list[i];
			int currentMaxIndex = i;

			for (int j = 0; j < i; j++) {
				if (currentMax < list[j]) {
					currentMax = list[j];
					currentMaxIndex = j;
				}
			}

			if (currentMaxIndex != i) {
				list[currentMaxIndex] = list[i];
				list[i] = currentMax;
			}
		}
	}

}
