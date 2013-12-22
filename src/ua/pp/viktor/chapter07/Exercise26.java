package ua.pp.viktor.chapter07;

import java.util.Scanner;

public class Exercise26 {

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
		double[][] sortedMatrix = sortRows(matrix);
		System.out.println("The row-sorted array is");
		for (int i = 0; i < sortedMatrix.length; i++) {
			for (int j = 0; j < sortedMatrix[i].length; j++) {
				System.out.print(sortedMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static double[][] sortRows(double[][] m) {
		double[][] result = new double[m.length][m[0].length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				result[i][j] = m[i][j];
			}
			bubbleSort(result[i]);
		}
		return result;
	}
	
	public static void bubbleSort(double[] list) {
		for(int i = 0; i < list.length - 1; i++) {
			for(int j = 0; j < list.length - 1; j++) {
				if (list[j] > list[j + 1]) {
					double tmp = list[j];
					list[j] = list [j + 1];
					list[j + 1] = tmp;
				}
			}
		}
	}

}
