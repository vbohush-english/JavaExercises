package ua.pp.viktor.chapter07;

import java.util.Scanner;

public class Exercise02 {

	public static void main(String[] args) {
		System.out.println("Enter a 4-by-4 matrix row by row:");
		Scanner input = new Scanner(System.in);
		final int SIZE = 4;
		double[][] matrix = new double[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] = input.nextDouble();				
			}
		}
		input.close();
		System.out.println("Sum of the elements in the major diagonal is " + sumMajorDiagonal(matrix));
	}

	public static double sumMajorDiagonal(double[][] m) {	
		double result = 0;
		for (int i = 0; i < m.length; i++) {
			result += m[i][i];
		}
		return result;
	}
}
