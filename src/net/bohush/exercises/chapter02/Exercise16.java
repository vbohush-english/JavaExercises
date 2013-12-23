package net.bohush.exercises.chapter02;

import java.util.Scanner;

public class Exercise16 {

	public static void main(String[] args) {
		System.out.print("Enter the side: ");
		Scanner input = new Scanner(System.in);
		double side = input.nextDouble();
		input.close();
		double area = ((3 * Math.sqrt(3)) / 2) * side * side;
		System.out.println("The area of the hexagon is " + area);
	}

}
