package net.bohush.exercises.chapter03;

import java.util.Scanner;

public class Exercise04 {

	public static void main(String[] args) {
		int number1 = (int)(Math.random() * 100);
		int number2 = (int)(Math.random() * 100);
		Scanner input = new Scanner(System.in);
		System.out.print("What is " + number1 + " + " + number2 + "? ");
		int answer = input.nextInt();
		input.close();
		System.out.println(number1 + " + " + number2 + " = " + answer + 
				" is " + (number1 + number2 == answer));
	}

}
