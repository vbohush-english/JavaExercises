package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise27 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a 3-by-3 matrix row by row: ");
		double[][] matrix = new double[3][3];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = input.nextDouble();
			}
		}
		double[][] sortedMatrix = sortColumns(matrix);
		System.out.println("The column-sorted array is");
		for (int i = 0; i < sortedMatrix.length; i++) {
			for (int j = 0; j < sortedMatrix[i].length; j++) {
				System.out.print(sortedMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static double[][] sortColumns(double[][] m) {
		double[][] result = new double[m.length][m[0].length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				result[i][j] = m[i][j];
			}
			java.util.Arrays.sort(result[i]);
		}
		return result;
	}
	
}
