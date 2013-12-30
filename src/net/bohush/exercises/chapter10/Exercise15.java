package net.bohush.exercises.chapter10;

import java.util.Scanner;

public class Exercise15 {

	public static void main(String[] args) {
		System.out.print("Enter five points: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		double[][] points = new double[5][2];
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[i].length; j++) {
				points[i][j] = input.nextDouble();
			}
		}
		MyRectangle2D r1 = getRectangle(points);
		System.out.println("The bounding rectangle's center (" + r1.getX() + ", " + r1.getY() + "), width " + r1.getWidth() + ", height " + r1.getHeight());
	}

	public static MyRectangle2D getRectangle(double[][] points) {
		double maxX = points[0][0];
		double minX = points[0][0];
		double maxY = points[0][1];
		double minY = points[0][1];
		for (int i = 1; i < points.length; i++) {
			if (points[i][0] > maxX) {
				maxX = points[i][0];
			}
			if (points[i][0] < minX) {
				minX = points[i][0];
			}
			if (points[i][1] > maxY) {
				maxY = points[i][1];
			}
			if (points[i][1] < minY) {
				minY = points[i][1];
			}
		}
		return new MyRectangle2D((maxX + minX) / 2, (maxY + minY) / 2, maxX - minX, maxY - minY);
	
	}
	
	
}
