package ua.pp.viktor.chapter04;

import java.util.Scanner;

public class Exercise07 {

	public static void main(String[] args) {
		System.out.print("Enter the tuition: ");
		Scanner input = new Scanner(System.in);
		double tuition = input.nextDouble();
		input.close();
		for (int i = 0; i < 10; i++) {
			tuition *= 1.05; 
		}
		System.out.println("Tuition in 10 years " + tuition);
		double total = 0;
		for (int i = 0; i < 4; i++) {
			total += tuition;
			tuition *= 1.05; 
		}		
		System.out.println("Total in 4 years " + total);
	}

}
