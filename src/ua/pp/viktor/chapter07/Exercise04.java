package ua.pp.viktor.chapter07;

public class Exercise04 {

	public static void main(String[] args) {
		int[][] hours = {{ 2, 4, 3, 4, 5, 8, 8 }, 
						{ 7, 3, 4, 3, 3, 4, 4 },
						{ 3, 3, 4, 3, 3, 2, 2 },
						{ 9, 3, 4, 7, 3, 4, 1 },
						{ 3, 5, 4, 3, 6, 3, 8 },
						{ 3, 4, 4, 6, 3, 4, 4 },
						{ 3, 7, 4, 8, 3, 8, 4 },
						{ 6, 3, 5, 9, 2, 7, 9 } };
		
		int[][] employees = new int[hours.length][2];
		
		for (int i = 0; i < hours.length; i++) {
			employees[i][0] = i;
			for (int j = 0; j < hours[i].length; j++) {
				employees[i][1] += hours[i][j];
			}
		}
		
		for (int i = 0; i < employees.length - 1; i++) {
			for (int j = i + 1; j < employees.length; j++) {
				if (employees[i][1] < employees[j][1]) {
					int tmp0 = employees[i][0];
					employees[i][0] = employees[j][0];
					employees[j][0] = tmp0;
					int tmp1 = employees[i][1];
					employees[i][1] = employees[j][1];
					employees[j][1] = tmp1;
				}
			}
		}
		
		for (int i = 0; i < employees.length; i++) {
			System.out.println("Employee " + employees[i][0] + " - " + employees[i][1]);
		}
	}

}
