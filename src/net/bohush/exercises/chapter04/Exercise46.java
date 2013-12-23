package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise46 {

	public static void main(String[] args) {
		System.out.print("Enter an integer: ");
		Scanner input = new Scanner(System.in);
		int integer = input.nextInt();
		input.close();
		String binaryInteger = "";
		for (int i = 0; i < 16; i++) {
			int tmp = integer >> i;
			binaryInteger = (tmp & 1) + binaryInteger;
			System.out.println(tmp);
		}
		System.out.println("The bits are " + binaryInteger);
		
	}

}
