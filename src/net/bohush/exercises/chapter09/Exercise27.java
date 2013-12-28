package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise27 {

	public static void main(String[] args) {
		System.out.print("Enter a genome string: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String genome = input.next();
		boolean found = false;
		for (int i = 0; i < genome.length() - 2; i++) {
			String tmp = genome.substring(i, i + 3);
			if (tmp.equals("ATG")) {
				found = true;
				for (int j = i + 3; j < genome.length() - 2; j += 3) {
					String nextGene = genome.substring(j, j + 3);
					if ((nextGene.equals("TAG")) || (nextGene.equals("TAA")) || (nextGene.equals("TGA"))) {
						break;
					}
					System.out.print(nextGene);	
				}
				System.out.println();	
			}
		}
		if (!found) {
			System.out.println("no gene is found");	
		}		
	}
		

}
