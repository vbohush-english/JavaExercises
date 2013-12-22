package ua.pp.viktor.chapter07;

import java.util.Scanner;

public class Exercise25 {

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
		System.out.println("It is " + (isMarkovMatrix(matrix) ? "" : "not ") + "a Markov matrix");
	}
	
	public static boolean isMarkovMatrix(double[][] m) {
		for (int i = 0; i < m.length; i++) {
			double sum = 0;
			for (int j = 0; j < m.length; j++) {
				if (m[j][i] < 0) {
					return false;
				}
				sum += m[j][i];
			}
			if (sum != 1.0) {
				return false;
			}
		}
		return true;
	}

}
