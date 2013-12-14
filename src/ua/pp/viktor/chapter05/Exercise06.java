package ua.pp.viktor.chapter05;

import java.util.Scanner;

public class Exercise06 {

	public static void main(String[] args) {
		System.out.print("Enter an integer: ");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		input.close();
		displayPattern(n);
	}

	public static void displayPattern(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = n; j > 0; j--) {
				System.out.print((j <= i ? j : " ") + " ");	
			}
			System.out.println();
		}
	}
}
