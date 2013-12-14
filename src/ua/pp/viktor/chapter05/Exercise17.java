package ua.pp.viktor.chapter05;

import java.util.Scanner;

public class Exercise17 {

	public static void main(String[] args) {
		System.out.print("Enter n: ");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		input.close();
		printMatrix(n);
	}
	
	
	public static void printMatrix(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((int)(Math.random() * 2) + " ");	
			}
			System.out.println();
		}
	}

}
