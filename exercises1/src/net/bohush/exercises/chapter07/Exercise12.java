package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise12 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("(0-single filer, 1-married jointly or qualifying widow(er), "
						+ "\n2-married separately, 3-head of household)\n"
						+ "Enter the filing status: ");
		int status = input.nextInt();
		System.out.print("Enter the taxable income: ");
		double income = input.nextDouble();
		input.close();
		double[] rates = {0.10, 0.15, 0.25, 0.28, 0.33, 0.35};
		int[][] brackets = {{8350, 33950, 82250, 171550, 372950}, // Single filer
							{16700, 67900, 137050, 20885, 372950}, // Married jointly or qualifying widow(er)
							{8350, 33950, 68525, 104425, 186475}, // Married separately
							{11950, 45500, 117450, 190200, 372950}};
		int i = 0;
		double tax = 0;
		while ((i < 5) && (brackets[status][i] < income)) {
			if (i == 0) {
				tax += brackets[status][i] * rates[i];
			} else {
				tax += (brackets[status][i] - brackets[status][i - 1]) * rates[i];				
			}
			i++;
		}
		if (i == 0) {
			tax += income * rates[i];	
		} else {
			tax += (income - brackets[status][i - 1]) * rates[i];			
		}
		
		System.out.println("Tax is " + tax);
	}


}
