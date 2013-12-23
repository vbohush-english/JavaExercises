package ua.pp.viktor.chapter07;

import java.util.Scanner;

public class Exercise32 {

	public static void main(String[] args) {
		System.out.print("Enter three points for a triangle: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		double[][] points = new double[3][2];
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[i].length; j++) {
				points[i][j] = input.nextDouble();
			}
		}
		double area = getTriangleArea(points);
		if (area == 0) {
			System.out.println("The three points are on the same line");
		} else {
			System.out.println("The area of the triangle is " + getTriangleArea(points));
		}
	}

	
	public static double getTriangleArea(double[][] points) {
		double side1 = Math.sqrt((points[1][0] - points[0][0]) * (points[1][0] - points[0][0]) + (points[1][1] - points[0][1]) * (points[1][1] - points[0][1]));
		double side2 = Math.sqrt((points[2][0] - points[1][0]) * (points[2][0] - points[1][0]) + (points[2][1] - points[1][1]) * (points[2][1] - points[1][1]));
		double side3 = Math.sqrt((points[0][0] - points[2][0]) * (points[0][0] - points[2][0]) + (points[0][1] - points[2][1]) * (points[0][1] - points[2][1]));
		double s = (side1 + side2 + side3) / 2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}
}
