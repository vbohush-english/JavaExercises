package net.bohush.exercises.chapter05;

import java.util.Scanner;

public class Exercise39 {

	public static void main(String[] args) {
		System.out.print("Enter three points for p0, p1, and p2: ");
		Scanner input = new Scanner(System.in);
		double x0 = input.nextDouble();
		double y0 = input.nextDouble();
		double x1 = input.nextDouble();
		double y1 = input.nextDouble();
		double x2 = input.nextDouble();
		double y2 = input.nextDouble();
		input.close();
		
		System.out.print("(" + x2 + ", " + y2 + ") is ");
		
		if (leftOfTheLine(x0, y0, x1, y1, x2, y2)) {
			System.out.print("on the left side of the line");
		} else if (rightOfTheLine(x0, y0, x1, y1, x2, y2)) {
			System.out.print("on the right side of the line");
		} else if (onTheLineSegment(x0, y0, x1, y1, x2, y2)) {
			System.out.print("on the line segment");
		} else {
			System.out.print("on the same line");
		}
		
		System.out.println(" from (" + x0 + ", " + y0 + ") to (" + x1 + ", " + y1 + ")");
	}
	
	public static boolean leftOfTheLine(double x0, double y0, double x1, double y1, double x2, double y2) {
		if (getPosition(x0, y0, x1, y1, x2, y2) > 0) {
			return true;
		} else {
			return false;
		}
	}

	
	public static boolean rightOfTheLine(double x0, double y0, double x1, double y1, double x2, double y2) {
		if (getPosition(x0, y0, x1, y1, x2, y2) < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean onTheSameLine(double x0, double y0, double x1, double y1, double x2, double y2) {
		if (getPosition(x0, y0, x1, y1, x2, y2) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean onTheLineSegment(double x0, double y0, double x1, double y1, double x2, double y2) {
		if (onTheSameLine(x0, y0, x1, y1, x2, y2) && (x2 < x1) && (x2 > x0) && (y2 < y1) && (y2 > y0)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static double getPosition(double x0, double y0, double x1, double y1, double x2, double y2) {
		return (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);	
	}
	

}
