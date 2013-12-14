package ua.pp.viktor.chapter05;

import java.util.Scanner;

public class Exercise23 {

	public static void main(String[] args) {
		System.out.print("Enter three points: ");
		Scanner input = new Scanner(System.in);
		double x1 = input.nextDouble();
		double y1 = input.nextDouble();
		double x2 = input.nextDouble();
		double y2 = input.nextDouble();
		double x3 = input.nextDouble();
		double y3 = input.nextDouble();
		input.close();
		double a = getSide(x2, y2, x3, y3);
		double b = getSide(x1, y1, x3, y3);
		double c = getSide(x1, y1, x2, y2);
		double angleA = getAngle(a, b, c);
		double angleB = getAngle(b, a, c);
		double angleC = getAngle(c, b, a);
		System.out.printf("The three angles are %.2f  %.2f  %.2f", angleA, angleB, angleC);
	}
	
	public static double getAngle(double a, double b, double c) {
		return Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
	}
	
	public static double getSide(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

}
