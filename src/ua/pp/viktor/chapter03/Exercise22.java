package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise22 {

	public static void main(String[] args) {
		System.out.print("Enter a point with two coordinates: ");
		Scanner input = new Scanner(System.in);
		double x = input.nextDouble();
		double y = input.nextDouble();
		input.close();
		
		double distance = Math.sqrt(x * x + y * y);
		double radius = 10.0;
		
		System.out.print("Point (" + x + ", " + y + ") is" + (distance > radius ? " not" : "") + " in the circle");
	}

}
