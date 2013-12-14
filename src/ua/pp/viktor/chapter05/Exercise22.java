package ua.pp.viktor.chapter05;

import java.util.Scanner;

public class Exercise22 {

	public static void main(String[] args) {
		System.out.print("Enter n: ");
		Scanner input = new Scanner(System.in);
		long n = input.nextLong();
		input.close();
		System.out.println(sqrt(n));
	}
	
	public static double sqrt(long n) {
		double lastGuess = 1;
		double nextGuess = (lastGuess + n / lastGuess) / 2.0;
		while (Math.abs(lastGuess - nextGuess) > 0.0001) {
			lastGuess = nextGuess;
			nextGuess = (lastGuess + n / lastGuess) / 2.0;
		}
		
		return nextGuess;
	}

}
