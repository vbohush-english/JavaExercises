package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise19 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of rows: ");
		int rows = input.nextInt();
		System.out.print("Enter the number of columns: ");
		int columns = input.nextInt();
		System.out.println();
		int[][] values = new int[rows][columns];
		input.close();
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				values[i][j] = (int)(Math.random() * 10);
				System.out.print(values[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println(isConsecutiveFour(values));
	}

	public static boolean isConsecutiveFour(int[][] values) {
		return isRowConsecutiveFour(values)||isColumnConsecutiveFour(values)||isDiagonal1ConsecutiveFour(values)||isDiagonal2ConsecutiveFour(values);
	}
	
	
	public static boolean isDiagonal2ConsecutiveFour(int[][] values) {
		for (int i = 0; i < values[0].length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j <= i; j++) {
				if ((i - j) >= values[0].length) {
					break;
				}
				if ((values.length - j - 1) < 0) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[values.length - j - 1][i - j];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[values.length - j - 1][i - j]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[values.length - j - 1][i - j];
					consecutiveCount = 1;
				}
			}
		}
		
		for (int i = 1; i < values.length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j < values.length - i; j++) {
				if ((values[0].length - j - 1) < 0) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[values.length - j - i - 1][values[0].length - j - 1];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[values.length - j - i - 1][values[0].length - j - 1]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[values.length - j - i - 1][values[0].length - j - 1];
					consecutiveCount = 1;
				}
			}
		}
		return false;
	}
	
	
	
	public static boolean isDiagonal1ConsecutiveFour(int[][] values) {
		for (int i = 0; i < values.length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j <= i; j++) {
				if (j >= values[0].length) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[i - j][j];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[i - j][j]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[i - j][j];
					consecutiveCount = 1;
				}
			}
		}
		for (int i = 1; i < values[0].length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j < values.length; j++) {
				if ((i + j) >= values[0].length) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[values.length - j - 1][i + j];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[values.length - j - 1][i + j]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[values.length - j - 1][i + j];
					consecutiveCount = 1;
				}
			}
		}
		return false;
	}


	
	public static boolean isRowConsecutiveFour(int[][] values) {
		int consecutiveCount = 0;
		int consecutiveNumber = 0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				if (consecutiveCount == 0) {
					consecutiveNumber = values[i][j];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[i][j]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[i][j];
					consecutiveCount = 1;
				}				
			}
			consecutiveCount = 0;
		}
		return false;
	}
	
	
	public static boolean isColumnConsecutiveFour(int[][] values) {
		int consecutiveCount = 0;
		int consecutiveNumber = 0;
		for (int i = 0; i < values[0].length; i++) {
			for (int j = 0; j < values.length; j++) {
				if (consecutiveCount == 0) {
					consecutiveNumber = values[j][i];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[j][i]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[j][i];
					consecutiveCount = 1;
				}				
			}
			consecutiveCount = 0;
		}
		return false;
	}
	
	
}
