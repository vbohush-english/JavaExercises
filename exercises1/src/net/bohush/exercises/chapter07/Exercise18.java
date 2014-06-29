package net.bohush.exercises.chapter07;

public class Exercise18 {

	public static void main(String[] args) {
		int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
		shuffle(m);
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();			
		}
	}
	
	public static void shuffle(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				int randomI = (int)(Math.random() * m.length);
				int randomJ = (int)(Math.random() * m[i].length);
				int tmp = m[i][j];
				m[i][j] = m[randomI][randomJ];
				m[randomI][randomJ] = tmp;
			}		
		}
	}

}
