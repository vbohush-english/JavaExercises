package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise41 {

	public static void main(String[] args) {
		System.out.print("Enter numbers: ");
		Scanner input = new Scanner(System.in);
		int max = input.nextInt();
		int count = 1;
		int next;
		do {
			next = input.nextInt();
			if (next > max) {
				max = next;
				count = 1;
			} else if (next == max) {
				count++;
			}
		} while (next != 0);
		input.close();
		System.out.println("The largest number is " + max);
		System.out.println("The occurrence count of the largest number is " + count);
	}

}
