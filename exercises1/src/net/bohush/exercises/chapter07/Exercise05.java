package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise05 {

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
		
		System.out.print("Enter matrix2: ");
		double[][] matrix2 = new double[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix2[i][j] = input.nextDouble();				
			}
		}
		
		input.close();
		System.out.println("The matrices are added as follows");
		double[][] matrix3 = addMatrix(matrix1, matrix2);
		
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

	public static double[][] addMatrix(double[][] a, double[][] b) {
		double[][] result = new double[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				result[i][j] = a[i][j] + b[i][j]; 
			}
		}
		return result;
	}
}
