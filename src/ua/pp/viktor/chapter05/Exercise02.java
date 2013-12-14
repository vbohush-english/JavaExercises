package ua.pp.viktor.chapter05;

import java.util.Scanner;

public class Exercise02 {

	public static void main(String[] args) {
		System.out.print("Enter an integer: ");
		Scanner input = new Scanner(System.in);
		long integer = input.nextLong();
		input.close();
		System.out.println("The sum of the digits in an integer volume is " + sumDigits(integer));

	}
	
	public static int sumDigits(long n) {
		int result = 0;
		while (n > 0) {
			result += n % 10;
			n /= 10;
		}
		return result;
	}

}
