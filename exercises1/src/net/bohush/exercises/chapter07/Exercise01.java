package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise01 {

	public static void main(String[] args) {
		System.out.println("Enter a 3-by-4 matrix row by row:");
		Scanner input = new Scanner(System.in);
		double[][] matrix = new double[3][4];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				matrix[i][j] = input.nextDouble();				
			}
		}
		input.close();
		for (int i = 0; i < 4; i++) {
			System.out.println("Sum of the elements at column " + i + " is " + sumColumn(matrix, i));
		}

	}

	public static double sumColumn(double[][] m, int columnIndex) {
		double result = 0;
		for (int i = 0; i < m.length; i++) {
			result += m[i][columnIndex];
		}
		return result;
	}
}
