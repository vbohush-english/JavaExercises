package net.bohush.exercises.chapter02;

import java.util.Scanner;

public class Exercise03 {

	public static void main(String[] args) {
		System.out.print("Enter a value for feet: ");
		Scanner input = new Scanner(System.in);
		double feet = input.nextDouble();
		input.close();
		System.out.println(feet + " feet is " + feet*0.305 + " meters");
	}

}
