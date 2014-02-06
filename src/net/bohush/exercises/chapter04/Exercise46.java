package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise46 {

	public static void main(String[] args) {
		System.out.print("Enter an integer: ");
		Scanner input = new Scanner(System.in);
		int integer = input.nextInt();
		input.close();
		String binaryInteger = "";
		int i = 0;
		int tmp = integer >> i;
		while (tmp != 0) {
			binaryInteger = (tmp & 1) + binaryInteger;
			i++;
			tmp = integer >> i;
		} 
		System.out.println("The bits are " + binaryInteger);
		
	}

}
