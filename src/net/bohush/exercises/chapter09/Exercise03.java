package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise03 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter password: ");
		String password = input.next();
		if (isPasswordGood(password)) {
			System.out.print("Valid Password");
		}else {
			System.out.print("Invalid Password");
		}
	}

	public static boolean isPasswordGood(String password) {
		if (password.length() < 8) {
			return false;
		}
		for (int i = 0; i < password.length(); i++) {
			if (!(Character.isDigit(password.charAt(i)) || Character.isAlphabetic(password.charAt(i)))) {
				return false;
			}
		}
		int numbersCount = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i))) {
				numbersCount++;
				if (numbersCount >= 2) {
					return true;
				}
			}
		}
		return false;
	}
}
