package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter an integer, the input ends if it is 0: ");
		int number = input.nextInt();
		int positives = 0;
		int negatives = 0;
		int total = 0;
		while (number != 0) {
			total += number;
			if (number > 0) {
				positives++;
			} else {
				negatives++;
			}
			number = input.nextInt();
		}
		input.close();
		if (total > 0) {
			System.out.println("The number of positives is " + positives);
			System.out.println("The number of negatives is " + negatives);
			System.out.println("The total is " + total);
			System.out.println("The average is " + (double)total / (positives + negatives));
		} else {
			System.out.println("No numbers are entered except 0");
		}
	}

}
