package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter binary string: ");
		String string = input.next();
		System.out.print(binaryToDecimal(string));
	}

	public static String binaryToDecimal(String binaryString) {
		long intRresult = 0;
		String result = "";
		long twoPow = 1;
		long hexPow = 1;
		for (int i = binaryString.length() - 1; i >= 0; i--) {
			if (binaryString.charAt(i) != '0') {
				intRresult += twoPow;
				if (intRresult > hexPow * 16) {
					result = getChar((intRresult - twoPow) / hexPow) + result;									
					hexPow *= 16;
				}
			}
			if (i == 0) {
				result = getChar(intRresult / hexPow) + result;
			}
			twoPow *= 2;
		}
		return result;
	}
	
	public static char getChar(long number) {
		if (number < 10) {
			return String.valueOf(number).charAt(0);	
		} else {
			return (char)('A' + (number - 10));	
		}		
	}
}
