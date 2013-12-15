package ua.pp.viktor.chapter05;

import java.util.Scanner;

public class Exercise36 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of sides: ");		
		int numberOfSides = input.nextInt();
		System.out.print("Enter the side: ");		
		double side = input.nextDouble();
		input.close();
		System.out.println("The area of the pentagon is " + area(numberOfSides, side));
	}
	
	public static double area(int n, double side) {
		return (n * side * side) / (4 * Math.tan(Math.PI/n));
	}

}
