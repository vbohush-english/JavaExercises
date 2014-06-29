package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise01 {

	public static void main(String[] args) {
		System.out.print("Enter a Social Security number: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String ssn = input.next();
		System.out.print(isSsnValid(ssn) ? "Valid SSN" : "Invalid SSN");
	}
	
	public static boolean isSsnValid(String ssn) {
		//valid - DDD-DD-DDDD
		if (ssn.length() != 11) {
			return false;
		}
		
		if (ssn.charAt(3) != '-') {
			return false;
		}
		
		if (ssn.charAt(6) != '-') {
			return false;
		}
		
		String newString = ssn.substring(0, 3) + ssn.substring(4, 6) + ssn.substring(7);
		for (int i = 0; i < newString.length(); i++) {
			if (!Character.isDigit(newString.charAt(i))) {
				return false;
			}
		}
	
		return true;
	}

}
