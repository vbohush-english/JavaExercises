package net.bohush.exercises.chapter07;

public class Exercise23 {

	public static void main(String[] args) {
		
		final int SIZE = 6;
		int[][] values = new int[SIZE][SIZE];
		do {
			for (int i = 0; i < values.length; i++) {
				for (int j = 0; j < values[i].length; j++) {
					values[i][j] = (int)(Math.random() * 2);
				}
			}
		} while (!(isRowEvenNumber(values) && isColumnEvenNumber(values)));
		int x = (int)(Math.random() * SIZE);
		int y = (int)(Math.random() * SIZE);
		values[x][y] = (values[x][y] == 0 ? 1 : 0);
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				System.out.print(values[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(findNotEvenRow(values) + ":" + findNotEvenColumn(values));
	}
	
	public static int findNotEvenRow(int[][] values) {
		int onesCount = 0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				if (values[i][j] == 1) {
					onesCount++;
				}				
			}
			if (onesCount % 2 == 1) {
				return i;
			}
		}
		return -1;
	}
	
	public static int findNotEvenColumn(int[][] values) {
		int onesCount = 0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				if (values[j][i] == 1) {
					onesCount++;
				}				
			}
			if (onesCount % 2 == 1) {
				return i;
			}
		}
		return -1;
	}
	
	
	public static boolean isRowEvenNumber(int[][] values) {
		int onesCount = 0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				if (values[i][j] == 1) {
					onesCount++;
				}				
			}
			if (onesCount % 2 == 1) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isColumnEvenNumber(int[][] values) {
		int onesCount = 0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				if (values[j][i] == 1) {
					onesCount++;
				}				
			}
			if (onesCount % 2 == 1) {
				return false;
			}
		}
		return true;
	}

}
