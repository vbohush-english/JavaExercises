package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise36 {

	public static void main(String[] args) {
		System.out.print("Enter the first 9 digits of an ISBN as integer: ");
		Scanner input = new Scanner(System.in);
		int isbn = input.nextInt();
		input.close();
		int d10 = 0;
		System.out.print("The ISBN-10 number is ");
		for (int i = 1, divisor = 100000000; i < 10; i++, divisor /= 10) {
			int tmp = isbn / divisor;
			d10 += tmp * i;
			isbn %= divisor;
			System.out.print(tmp);
		}		
		System.out.println(d10 % 11 == 10 ? "X" : d10 % 11);
	}

}
