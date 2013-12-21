package ua.pp.viktor.chapter07;

import java.util.Scanner;

public class Exercise17 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter number of banks: ");
		int n = input.nextInt();
		
		System.out.print("Enter limit: ");
		int limit = input.nextInt();
		double[] banks = new double[n];
		double[][] borrowers = new double[n][n];
		
		System.out.println("Enter data:");
		
		
		for (int i = 0; i < banks.length; i++) {
			
			banks[i] = input.nextDouble();
			int numberOfBanks = input.nextInt();
			
			for (int j = 0; j < numberOfBanks; j++) {
				int borrowersID = input.nextInt();
				double borrowersAmount = input.nextDouble();
				
				banks[i] += borrowersAmount;
				borrowers[i][borrowersID] = borrowersAmount;
			}
		}

		for (int i = 0; i < banks.length; i++) {
			if (banks[i] < limit) {
				for (int j = 0; j < banks.length; j++) {
					banks[j] -= borrowers[j][i];
				}
			}
		}
		System.out.print("Unsafe banks are: ");
		for (int i = 0; i < banks.length; i++) {
			if (banks[i] < limit) {
				System.out.print(i + " ");
			}
		}
		
	}

}
