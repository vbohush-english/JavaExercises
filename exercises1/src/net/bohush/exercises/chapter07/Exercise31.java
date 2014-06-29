package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise31 {

	public static void main(String[] args) {
		System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
		Scanner input = new Scanner(System.in);
		double[][] points = new double[4][2];
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[i].length; j++) {
				points[i][j] = input.nextDouble();
			}
		}
		input.close();
		double[] result = getIntersectingPoint(points);
		if (result == null) {
			System.out.println("The two lines are parallel");
		} else {
			System.out.println("The intersecting point is at (" + result[0] + ", " + result[1] + ")");
		}		
	}
	
	public static double[] getIntersectingPoint(double[][] points) {
		double a = points[0][1] - points[1][1];
		double c = points[2][1] - points[3][1];
		double b = points[0][0] - points[1][0];
		double d = points[2][0] - points[3][0];
		double e = a * points[0][0] - b * points[0][1];
		double f = c * points[2][0] - d * points[2][1];		
		if ((a * d - b * c) == 0) {
			return null;
		} else {
			double[] result = new double[2];
			result[0] = (e * d - b * f) / (a * d - b * c);
			result[1] = (a * f - e * c) / (a * d - b * c);
			return result;
		}
	}

}
