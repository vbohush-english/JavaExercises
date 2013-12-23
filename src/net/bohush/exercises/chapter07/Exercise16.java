package net.bohush.exercises.chapter07;

public class Exercise16 {

	public static void main(String[] args) {
		int[][] m = {{4, 2},{1, 7},{4, 5},{1, 2},{1, 1},{4, 1}};
		sort(m);
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + ", ");
			}
			System.out.println();
		}

	}
	
	public static void sort(int m[][]) {
		for (int i = 0; i < m.length - 1; i++) {
			for (int j = i + 1; j < m.length; j++) {
				if (m[i][0] > m[j][0]) {
					int tmp = m[i][0];
					m[i][0] = m[j][0];
					m[j][0] = tmp;
					tmp = m[i][1];
					m[i][1] = m[j][1];
					m[j][1] = tmp;
				} else if (m[i][0] == m[j][0]) {
					if (m[i][1] > m[j][1]) {
						int tmp = m[i][0];
						m[i][0] = m[j][0];
						m[j][0] = tmp;
						tmp = m[i][1];
						m[i][1] = m[j][1];
						m[j][1] = tmp;	
					}
				}
			}
		}	
	}

}
