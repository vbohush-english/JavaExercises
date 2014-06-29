package net.bohush.exercises.chapter03;

import java.util.Scanner;

public class Exercise25 {

	public static void main(String[] args) {
		System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
		Scanner input = new Scanner(System.in);
		double x1 = input.nextDouble();
		double y1 = input.nextDouble();
		double x2 = input.nextDouble();
		double y2 = input.nextDouble();
		double x3 = input.nextDouble();
		double y3 = input.nextDouble();
		double x4 = input.nextDouble();
		double y4 = input.nextDouble();
		input.close();
		
		double a = y1 - y2;
		double c = y3 - y4;
		double b = x1 - x2;
		double d = x3 - x4;
		double e = a * x1 - b * y1;
		double f = c * x3 - d * y3;
		
		if ((a * d - b * c) == 0) {
			System.out.println("The two lines are parallel");
		} else {
			double x = (e * d - b * f) / (a * d - b * c);
			double y = (a * f - e * c) / (a * d - b * c);
			System.out.println("The intersecting point is at (" + x + ", " + y + ")");
		}
		
		
	}

}
