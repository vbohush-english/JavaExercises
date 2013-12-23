package net.bohush.exercises.chapter02;

import java.util.Scanner;

public class Exercise02 {

	public static void main(String[] args) {
		System.out.print("Enter the radius and length of a cylinder: ");
		Scanner input = new Scanner(System.in);
		double radius = input.nextDouble();
		double length = input.nextDouble();
		input.close();
		final double P = 3.1415926;
		double area = radius * radius * P;
		double volume = area * length;
		System.out.println("The area is " + (int)(area * 100) / 100.0);
		System.out.println("The volume is " + (int)(volume * 100) / 100.0);
	}
	
}
