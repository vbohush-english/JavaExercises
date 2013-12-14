package ua.pp.viktor.chapter05;

import java.util.Scanner;
public class Exercise19 {

	public static void main(String[] args) {
		System.out.print("Enter three sides of triangle: ");
		Scanner input = new Scanner(System.in);
		double side1 = input.nextDouble();
		double side2 = input.nextDouble();
		double side3 = input.nextDouble();
		input.close();
		
		if (isValid(side1, side2, side3)) {
			System.out.println("The area of triangle is " + area(side1, side2, side3));
		} else {
			System.out.println("The input is invalid");			
		}
	}
	
	public static boolean isValid(double side1, double side2, double side3) {
		if ((side1 >= side2 + side3) || (side2 >= side1 + side3) || (side3 >= side1 + side2)) {
			return false;
		} else {
			return true;
		}			
	}

	public static double area(double side1, double side2, double side3) {
		double s = (side1 + side2 + side3) / 2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}
}
