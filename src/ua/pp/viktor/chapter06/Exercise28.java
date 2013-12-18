package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise28 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		final int NUMBER_OF_INTEGERS = 10;
		System.out.print("Enter " + NUMBER_OF_INTEGERS + " integers: ");		
		int[] integers = new int[NUMBER_OF_INTEGERS];
		for (int i = 0; i < integers.length; i++) {
			integers[i] = input.nextInt();
		}
		input.close();
		
	    for (int i = 0; i < NUMBER_OF_INTEGERS; i++) 
	        for (int j = i + 1; j < NUMBER_OF_INTEGERS; j++)
	          System.out.println(integers[i] + "\t" + integers[j]);
	}

}
