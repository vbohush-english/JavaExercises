package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise25 {

	public static void main(String[] args) {
		String[] words = {"write", "that", "program", "java"};
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		do {
			String word = words[(int)(Math.random() * words.length)];
			boolean[] guessedChars = new boolean[word.length()];
			int wrongGuess = 0;
			while(!isGuessed(guessedChars)) {
				char nextChar = getNextChar(word, guessedChars);
				if (!putChar(nextChar, word, guessedChars)) {
					wrongGuess++;
					System.out.println("   " + nextChar + " is not in the word");
				}
			}
			System.out.println("The word is " + word + ". You missed " + wrongGuess + " time" + (wrongGuess > 1 ? "s" : ""));
			System.out.print("Do you want to guess another word? Enter y or n > ");			
		} while (input.next().charAt(0) == 'y');
	}

	public static boolean isGuessed(boolean[] guessedChars) {
		for (int i = 0; i < guessedChars.length; i++) {
			if (!guessedChars[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean putChar(char nextChar, String word, boolean[] guessedChars) {
		boolean result = false;
		for (int i = 0; i < word.length(); i++) {
			if (nextChar == word.charAt(i)) {
				if (guessedChars[i]) {
					System.out.println("   " + nextChar + " is already in the word");
					return true;
				} else {
					guessedChars[i] = true;
					result = true;	
				}				
			}
		}
		return result;
	}
	
	public static char getNextChar(String word, boolean[] guessedChars) {
		System.out.print("(Guess) Enter a letter in word ");
		for (int i = 0; i < guessedChars.length; i++) {
			if (guessedChars[i]) {
				System.out.print(word.charAt(i));
			} else {
				System.out.print('*');
			}
		}
		System.out.print(" > ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		return input.next().charAt(0);
	}
	
	
}
