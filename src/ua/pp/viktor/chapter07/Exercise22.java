package ua.pp.viktor.chapter07;

public class Exercise22 {

	public static void main(String[] args) {
		
		final int SIZE = 6;
		int[][] values = new int[SIZE][SIZE];
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				values[i][j] = (int)(Math.random() * 2);
				System.out.print(values[i][j] + " ");
			}
			System.out.println();
		}	
		System.out.println(isRowEvenNumber(values) && isColumnEvenNumber(values));
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
