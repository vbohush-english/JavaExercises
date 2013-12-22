package ua.pp.viktor.chapter07;

import java.util.Scanner;

public class Exercise14 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the size for the matrix: ");
		int size = input.nextInt();
		input.close();
		
		
		int array[][] = new int[size][size];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = (int)(Math.random() * 2);
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
		

		if (!(printSameRows(0, array))&(!printSameRows(1, array))) {
				System.out.println("No same numbers on a row");	
		}

		
		if (!(printSameColumns(0, array))&(!printSameColumns(1, array))) {
			System.out.println("No same numbers on a column");	
		}
		
		if (!(printDiagonal(0, array))&(!printDiagonal(1, array))) {
			System.out.println("No same numbers on the major diagonal");	
		}
		
		if (!(printSubDiagonal(0, array))&(!printSubDiagonal(1, array))) {
			System.out.println("No same numbers on the sub-diagonal");	
		}
		
	}
	
	public static boolean printSubDiagonal(int number, int[][] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i][array.length - i -1] != number) {
				return false;
			}
		}
		System.out.println("All " + number + "s on the sub-diagonal");
		return true;
	}
	
	public static boolean printDiagonal(int number, int[][] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i][i] != number) {
				return false;
			}
		}
		System.out.println("All " + number + "s on the major diagonal");
		return true;
	}

	public static boolean printSameColumns(int number, int[][] array) {
		boolean isColumns = false;
		for (int i = 0; i < array.length; i++) {
			if (isColumnSame(number, i, array)) {
				System.out.println("All " + number + "s on column " + i);
				isColumns = true;
			}
		}
		return isColumns;
	}
	
	public static boolean isColumnSame(int number, int column, int[][] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i][column] != number) {
				return false;
			}
		}
		return true;
	}
	
	

	public static boolean printSameRows(int number, int[][] array) {
		boolean isRows = false;
		for (int i = 0; i < array.length; i++) {
			if (isRowSame(number, i, array)) {
				System.out.println("All " + number + "s on row " + i);
				isRows = true;
			}
		}
		return isRows;
	}
	
	public static boolean isRowSame(int number, int row, int[][] array) {
		for (int i = 0; i < array[row].length; i++) {
			if (array[row][i] != number) {
				return false;
			}
		}
		return true;
	}
	

}
