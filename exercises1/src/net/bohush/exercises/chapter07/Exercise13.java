package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise13 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of rows and columns of the array: ");
		int rows = input.nextInt();
		int columns = input.nextInt();
		System.out.println("Enter the array: ");
		double[][] array = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				array[i][j] = input.nextDouble();				
			}
		}
		double max = array[0][0];
		int maxRow = 0;
		int maxColumn = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 1; j < columns; j++) {
				if (max < array[i][j]) {
					max = array[i][j];
					maxRow = i;
					maxColumn = j;
				}
			}
		}
		input.close();
		System.out.println("The location of the largest element is at (" + maxRow + ", " + maxColumn + ")");
	}

}
