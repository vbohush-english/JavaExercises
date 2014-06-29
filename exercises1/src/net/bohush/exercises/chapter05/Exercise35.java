package net.bohush.exercises.chapter05;

import java.util.Scanner;

public class Exercise35 {

	public static void main(String[] args) {
		System.out.print("Enter the side: ");
		Scanner input = new Scanner(System.in);
		double side = input.nextDouble();
		input.close();
		double area = (5 * side * side) / (4 * Math.tan(Math.PI/5));
		System.out.println("The area of the pentagon is " + area);
	}

}
