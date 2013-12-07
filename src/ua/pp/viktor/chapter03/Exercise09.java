package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		System.out.print("Enter the first 9 digits of an ISBN as integer: ");
		Scanner input = new Scanner(System.in);
		int isbn = input.nextInt();
		input.close();
		int d1 = isbn / 100000000;
		isbn -= d1 * 100000000;
		int d2 = isbn / 10000000;
		isbn -= d2 * 10000000;
		int d3 = isbn / 1000000;
		isbn -= d3 * 1000000;
		int d4 = isbn / 100000;
		isbn -= d4 * 100000;
		int d5 = isbn / 10000;
		isbn -= d5 * 10000;
		int d6 = isbn / 1000;
		isbn -= d6 * 1000;
		int d7 = isbn / 100;
		isbn -= d7 * 100;
		int d8 = isbn / 10;
		isbn -= d8 * 10;
		int d9 = isbn;
		int d10 = (d1 * 1 + d2 * 2 + d3 * 3 + d4 * 4 + d5 * 5 +d6 * 6 + d7 * 7 +d8 * 8 + d9 * 9) % 11;
		System.out.print("The ISBN-10 number is " + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9);
		if (d10 == 10) {
			System.out.println('X');
		} else {
			System.out.println(d10);
		}
		
	}

}
