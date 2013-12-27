package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise23 {


	public static void main(String[] args) {
		System.out.print("Enter a credit card number as a long integer:\n  ");
		Scanner input = new Scanner(System.in);
		String cardNumber = input.next();
		input.close();
		System.out.println(cardNumber + " is " + (isValid(cardNumber)? "" : "in") + "valid");
	}
	
	public static boolean isValid(String cardNumber) {
		if ((cardNumber.length() < 13) || (cardNumber.length() > 16)) {
			return false;	
		}
		if ((cardNumber.charAt(0) == '4') ||
				(cardNumber.charAt(0) == '5') ||
				(cardNumber.charAt(0) == '6') ||
				((cardNumber.charAt(0) == '3') && (cardNumber.charAt(1) == '7'))) {
			if ((sumOfOddPlace(cardNumber) + sumOfDoubleEvenPlace(cardNumber)) % 10 == 0) {
				return true;	
			} else {
				return false;		
			}	
		} else {
			return false;
		}
	}
	
	public static int sumOfDoubleEvenPlace(String cardNumber) {
		int result = 0;
		for (int i = cardNumber.length() - 2; i >= 0; i -= 2) {
			int number = Character.getNumericValue(cardNumber.charAt(i));
			result += getDigit(number * 2);
		}			
		return result;
	}
	
	public static int sumOfOddPlace(String cardNumber) {
		int result = 0;
		for (int i = cardNumber.length() - 1; i >= 0; i -= 2) {
			result +=  Character.getNumericValue(cardNumber.charAt(i));
		}			
		return result;
	}
	
	public static int getDigit(int number) {
		if (number < 10) {
			return number;
		} else {
			return 1 + number % 10;
		}
	}
	

}
