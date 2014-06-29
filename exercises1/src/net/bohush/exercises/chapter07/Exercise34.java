package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise34 {

	public static void main(String[] args) {
		System.out.print("Enter 6 points: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		double[][] points = new double[6][2];
		//1,5 2,5 -3 4,5 5,6 -7 6,5 -7 8 1 10 2,5
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[i].length; j++) {
				points[i][j] = input.nextDouble();
			}
		}
		double[] result = getRightmostLowestPoint(points);
		System.out.println("The rightmost lowest point is (" + result[0] + ", " + result[1] + ")");
	}
	
	public static double[] getRightmostLowestPoint(double[][] points) {
		double pointX = points[0][0];
		double pointY = points[0][1];
		for (int i = 0; i < points.length; i++) {
			if (points[i][1] < pointY) {
				pointX = points[i][0];
				pointY = points[i][1];
			} else if ((points[i][1] == pointY) && (points[i][0] > pointX)) {
				pointX = points[i][0];
			}
		}
		return new double[]{pointX, pointY};
	}

}
