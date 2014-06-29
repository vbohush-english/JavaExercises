package net.bohush.exercises.chapter07;

public class Exercise10 {

	public static void main(String[] args) {
		final int SIZE = 4;
		int array[][] = new int[SIZE][SIZE];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = (int)(Math.random() * 2);
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
		int row = 0;
		int column = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (get1s(i, j, array) > get1s(column, row, array)) {
					column = i;
					row = j;					
				}
			}
		}
		System.out.println("The largest row index: " + row);
		System.out.println("The largest column index: " + column);
	}

	public static int get1s(int columnNumber, int rowNumber, int[][] array) {
		int ones = getRows(rowNumber, array) + getColumns(columnNumber, array);
		if (array[rowNumber][columnNumber] == 1) {
			ones--;
		}
		return ones;
	}
	
	public static int getRows(int columnNumber, int[][] array) {
		int columns = 0;
		for (int i = 0; i < array[columnNumber].length; i++) {
			if (array[columnNumber][i] == 1) {
				columns++;
			}
		}
		return columns;
	}
	
	public static int getColumns(int rowNumber, int[][] array) {
		int rows = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i][rowNumber] == 1) {
				rows++;
			}
		}
		return rows;
	}

}
