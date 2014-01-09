package net.bohush.exercises.chapter14;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise02 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		boolean continueAsk = true;
		while (continueAsk) {
			try {
				System.out.print("Enter x: ");
				int x = input.nextInt();
				System.out.print("Enter y: ");
				int y = input.nextInt();
				System.out.println("Sum of two integers: " + (x + y));
				continueAsk = false;
			} catch (InputMismatchException ex) {
				System.out.print("Wrong input. ");
				input.nextLine();
			}
		}
	}

}
