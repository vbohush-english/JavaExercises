package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise15 {

	public static void main(String[] args) {
		System.out.print("Enter five points: ");
		Scanner input = new Scanner(System.in);
		final int POINTS_COUNT = 5;
		final int COORDINATS = 2;
		double[][] points = new double[POINTS_COUNT][COORDINATS];
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[i].length; j++) {
				points[i][j] = input.nextDouble(); 
			}
		}
		input.close();
		System.out.print("The five points are " + (sameLine(points) ? "" : "not ") + "on the same line");
	}
	
	
	public static boolean sameLine(double[][] points) {
		for (int i = 1; i < points.length - 1; i++) {
			if (!onTheSameLine(points[0][0], points[0][1],
								points[points.length - 1][0], points[points.length - 1][1],
								points[i][0], points[i][1])) {
				return false;
			}
		}
		return true;
	}
	
		
	public static boolean onTheSameLine(double x0, double y0, double x1, double y1, double x2, double y2) {
		if (getPosition(x0, y0, x1, y1, x2, y2) == 0) {
			return true;
		} else {
			return false;
		}
	}

	
	public static double getPosition(double x0, double y0, double x1, double y1, double x2, double y2) {
		return (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);	
	}
	

}
