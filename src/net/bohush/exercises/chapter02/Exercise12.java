package net.bohush.exercises.chapter02;

import java.util.Scanner;

public class Exercise12 {

	public static void main(String[] args) {
		System.out.print("Enter speed and acceleration: ");
		Scanner input = new Scanner(System.in);
		double speed = input.nextDouble();
		double acceleration = input.nextDouble();
		input.close();
		double length = (speed * speed) / (2 * acceleration);
		System.out.println("The minimum runway length for this airplane is " + length);
	}

}
