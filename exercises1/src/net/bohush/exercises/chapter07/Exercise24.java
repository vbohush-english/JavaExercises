package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise24 {

	public static void main(String[] args) {
		int[][] grid = readASolution();
		System.out.println(isValid(grid) ? "Valid solution" : "Invalid solution");
	}

	public static boolean isValid(int[][] grid) {
		int[] tmp = new int[9];
		
		//rows
		for (int i = 0; i < 9; i++) {
			System.arraycopy(grid[i], 0, tmp, 0, 9);
			if (!checkLine(tmp)) {
				return false;
			}
		}
		
		//columns
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				tmp[j] = grid[j][i];
			}
			if (!checkLine(tmp)) {
				return false;
			}
		}	
		
		//box
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int index = 0;
				for (int row = i * 3; row < i * 3 + 3; row++) {
					for (int column = j * 3; column < j * 3 + 3; column++) {
						tmp[index++] = grid[row][column];
					}					
				}
				if (!checkLine(tmp)) {
					return false;
				}
			}

		}
		

		return true;
	}
	
	
	public static boolean checkLine(int[] line) {
		java.util.Arrays.sort(line);
		for (int i = 0; i < 9; i++ ) {
			if (line[i] != i + 1) {
				return false;
			}
		}
		return true;
	}
	
	
	public static int[][] readASolution() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		System.out.println("Enter a Sudoku puzzle solution:");
		int[][] grid = new int[9][9];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				grid[i][j] = input.nextInt();
		return grid;
	}
	

}
