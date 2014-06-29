package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise35 {

	public static void main(String[] args) {
		System.out.print("Enter the number of rows in the matrix: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int size = input.nextInt();
		int[][] matrix = new int[size][size];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = (int)(Math.random() * 2);
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		int[] submatrix = new int[3];
		submatrix = findLargestBlock(matrix);
		System.out.println("The maximum square submatrix is at (" + submatrix[0] + ", " + submatrix[1] + ") with size " + submatrix[2]);
	}
	
	public static int[] findLargestBlock(int[][] m) {
		int[] submatrix = new int[3];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] == 1) {
					int size = getSubMatrixSize(i, j, m);
					if (size > submatrix[2]) {
						submatrix[0] = i;
						submatrix[1] = j;
						submatrix[2] = size;
					}
					
				}
			}
		}
		return submatrix;
	}
	
	
	public static int getSubMatrixSize(int row, int column, int[][] m) {
		int result = 0;
		for (int i = row; i < m.length; i++) {
			for (int j = column; j < m[i].length; j++) {
				for (int size = 1; size < m.length; size++) {
					if (!isAll1s(row, column, size, m)) {
						if (result < (size - 1)) {
							result = size - 1;
						}
						break;
					}
				}
			}
		}
		return result;
	}
	
	public static boolean isAll1s(int row, int column, int size, int[][] m) {
		for (int i = row; i < row + size; i++) {
			if (i >= m.length) {
				return false;
			}
			for (int j = column; j < column + size; j++) {
				if (j >= m[i].length) {
					return false;
				}
				if (m[i][j] != 1) {
					return false;
				}

			}
		}
		return true;
	}
	
	
	

}
