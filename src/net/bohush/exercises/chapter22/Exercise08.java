package net.bohush.exercises.chapter22;

import java.util.LinkedList;
import java.util.Scanner;

public class Exercise08 {
	public static void main(String[] args) {
		final int SIZE = 3;

		LinkedList<Integer> lotteryDigits = new LinkedList<>();
		for (int i = 0; i < SIZE; i++)
			lotteryDigits.add((int) (Math.random() * 10));

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter your lottery pick (three digits): ");
		String guess = input.next();
		if (guess.length() != 3) {
			System.out.println("Error: invalid parametrs");
			System.exit(1);
		}

		LinkedList<Integer> guessDigits = new LinkedList<>();
		for (int i = 0; i < SIZE; i++)
			guessDigits.add(Character.digit(guess.charAt(i), 10));

		System.out.println("The lottery number is " + lotteryDigits.get(0) + lotteryDigits.get(1) + lotteryDigits.get(2));

		if (lotteryDigits.equals(guessDigits)) {
			System.out.println("Exact match: you win $10,000");
		} else {
			lotteryDigits.retainAll(guessDigits);
			switch (lotteryDigits.size()) {
			case 1: System.out.println("Match one digit: you win $1,000"); break;
			case 2: System.out.println("Match two digits: you win $2,000"); break;
			case 3: System.out.println("Match all digits: you win $3,000"); break;
			default: System.out.println("Sorry, no match");
			}
		}
	}
}
