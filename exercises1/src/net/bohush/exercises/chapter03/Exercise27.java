package net.bohush.exercises.chapter03;

import java.util.Scanner;

public class Exercise27 {

	public static void main(String[] args) {
		System.out.print("Enter a point's x- and y-coordinates: ");
		Scanner input = new Scanner(System.in);
		double x = input.nextDouble();
		double y = input.nextDouble();
		input.close();
		int x1 = 200;
		int y1 = 0;
		int x2 = 0;
		int y2 = 100;	
		double side = (x1 - x2) * (y - y2) - (x - x2) * (y1 - y2);
		System.out.print("The point is" + ((side > 0) || (x < 0) || (y < 0) ? " not" : "") + " in the triangle");
	}

}
