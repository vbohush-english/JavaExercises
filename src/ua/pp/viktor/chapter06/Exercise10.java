package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise10 {

	public static void main(String[] args) {
		System.out.print("Enter ten numbers: ");
		double[] array = new double[10];
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < array.length; i++) {
			array[i] = input.nextDouble();
		}
		input.close();
		System.out.println("The minimum number is: " + Exercise09.min(array));
		System.out.println("The index of the smallest element: " + indexOfSmallestElement(array));
	}
	
	public static int indexOfSmallestElement(double[] array) {
		int minIndex = 0;
		double min = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
				minIndex = i;
			}
		}
		return minIndex;		
	}

}