package ua.pp.viktor.chapter05;

import java.util.Scanner;

public class Exercise31 {

	public static void main(String[] args) {
		System.out.print("Enter a credit card number as a long integer:\n  ");
		Scanner input = new Scanner(System.in);
		long cardNumber = input.nextLong();
		input.close();
		System.out.println(cardNumber + " is " + (isValid(cardNumber)? "" : "in") + "valid");
		
	}
	
	/** Return true if the card number is valid */
	public static boolean isValid(long number) {
		if ((getSize(number) < 13) || (getSize(number) > 16)) {
			return false;	
		} else {
			if ((prefixMatched(number, 4)) || (prefixMatched(number, 5)) || (prefixMatched(number, 37)) || (prefixMatched(number, 6))) {
				if ((sumOfOddPlace(number) + sumOfDoubleEvenPlace(number)) % 10 == 0) {
					return true;	
				} else {
					return false;		
				}				
			} else {
				return false;	
			}
		}
	}
	
	/** Get the result from Step 2 */
	public static int sumOfDoubleEvenPlace(long number) {
		int result = 0;
		number /= 10;
		while (number > 0) {
			result += getDigit((int)((2*(number % 10))));
			number = number / 100;
		}
		return result;
	}
	
	/** Return this number if it is a single digit, otherwise,
	* return the sum of the two digits */
	public static int getDigit(int number) {
		if (number < 10) {
			return number;
		} else {
			return 1 + number % 10;
		}
	}
	
	/** Return sum of odd-place digits in number */
	public static int sumOfOddPlace(long number) {
		int result = 0;
		while (number > 0) {
			result += number % 10;
			number = number / 100;
		}
		return result;
	}
	
	/** Return true if the digit d is a prefix for number */
	public static boolean prefixMatched(long number, int d) {
		if (number - (d * (long)Math.pow(10, getSize(number) - getSize(d))) ==
				number % (long)Math.pow(10, getSize(number) - getSize(d))) {
			return true;	
		} else {
			return false;
		}		
	}
	
	/** Return the number of digits in d */
	public static int getSize(long d) {
		if (d <= 0) {
			return 0;
		}
		int result = 1;
		while (d > 10) {
			result++;
			d /= 10;
		}
		return result;
	}

}
