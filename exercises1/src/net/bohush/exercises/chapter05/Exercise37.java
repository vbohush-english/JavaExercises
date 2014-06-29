package net.bohush.exercises.chapter05;

import java.util.Scanner;

public class Exercise37 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number: ");		
		int number = input.nextInt();
		System.out.print("Enter the width: ");		
		int width = input.nextInt();
		input.close();
		System.out.println(format(number, width));
	}
	
	public static String format(int number, int width) {
		int sizeOfNumber = Exercise31.getSize(number);
		String zeros = "";
		while (width > sizeOfNumber) {
			zeros += "0";
			width--;
		}
		return zeros + number;
	}

}
