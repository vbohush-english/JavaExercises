package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise08 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of points: ");
		int numberOfPoints = input.nextInt();

		// Create an array to store points
		double[][] points = new double[numberOfPoints][2];
		System.out.print("Enter " + numberOfPoints + " points: ");
		for (int i = 0; i < points.length; i++) {//-1 3 -1 -1 1 1 2 0,5 2 -1 3 3 4 2 4 -0,5 2 2 3 1,5
			points[i][0] = input.nextDouble();
			points[i][1] = input.nextDouble();
		}
		input.close();

		double shortestDistance = distance(points[0][0], points[0][1],
				points[1][0], points[1][1]); // Initialize shortestDistance

		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				double distance = distance(points[i][0], points[i][1],
						points[j][0], points[j][1]); // Find distance
				if (shortestDistance > distance) {
					shortestDistance = distance; // Update shortestDistance
				}
			}
		}
		
		
		System.out.println("Points with the same minimum distance:");		
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				double distance = distance(points[i][0], points[i][1],
						points[j][0], points[j][1]); // Find distance

				if (shortestDistance == distance) {
					shortestDistance = distance; // Update shortestDistance
					System.out.println("(" + points[i][0]
							+ ", " + points[i][1] + ") and (" + points[j][0] + ", "
							+ points[j][1] + ")");
				}
			}
		}
		
		

		// Display result

	}

	/** Compute the distance between two points (x1, y1) and (x2, y2) */
	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

}
