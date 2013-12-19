package ua.pp.viktor.chapter07;

import java.util.Scanner;

public class Exercise06 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		final int SIZE = 3;
		
		System.out.print("Enter matrix1: ");		
		double[][] matrix1 = new double[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix1[i][j] = input.nextDouble();				
			}
		}
		
		System.out.print("Enter matrix2: ");	//0 2 4 1 4,5 2,2 1,1 4,3 5,2	
		double[][] matrix2 = new double[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix2[i][j] = input.nextDouble();				
			}
		}
		
		input.close();
		System.out.println("The multiplication of the matrices is");
		double[][] matrix3 = multiplyMatrix(matrix1, matrix2);
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.printf("%.2f ", matrix1[i][j]);
			}
			System.out.print("  " + (i == 1 ? "+" : " ") + "  ");
			for (int j = 0; j < SIZE; j++) {
				System.out.printf("%.2f ", matrix2[i][j]);
			}
			System.out.print("  " + (i == 1 ? "=" : " ") + "  ");
			for (int j = 0; j < SIZE; j++) {
				System.out.printf("%.2f ", matrix3[i][j]);
			}
			System.out.println();
		}
	}

	public static double[][] multiplyMatrix(double[][] a, double[][] b) {
		double[][] result = new double[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				result[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j] + a[i][2] * b[2][j];
			}
		}
		return result;
	}
}
