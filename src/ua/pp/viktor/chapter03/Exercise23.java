package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise23 {

	public static void main(String[] args) {
		System.out.print("Enter a point with two coordinates: ");
		Scanner input = new Scanner(System.in);
		double x = input.nextDouble();
		double y = input.nextDouble();
		input.close();
	
		System.out.print("Point (" + x + ", " + y + ") is" + (((x > 10.0 / 2)||(y > 5.0 / 2)) ? " not" : "") + " in the rectangle");
	}

}
