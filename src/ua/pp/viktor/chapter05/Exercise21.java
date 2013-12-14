package ua.pp.viktor.chapter05;

import java.util.Scanner;

public class Exercise21 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter point 1 (latitude and longitude) in degrees: \n  ");
		double latitude1 = input.nextDouble();
		double longitude1 = input.nextDouble();
		System.out.print("Enter point 2 (latitude and longitude) in degrees: \n  ");
		double latitude2 = input.nextDouble();
		double longitude2 = input.nextDouble();
		input.close();
		System.out.println("The distance between the two points is " + getDistance(latitude1, longitude1, latitude2, longitude2) + " km");
	}
	
	
	public static double getDistance(double x1, double y1, double x2, double y2) {
		final double RADIUS = 6371.01;
		return RADIUS * Math.acos(Math.sin(Math.toRadians(x1)) * Math.sin(Math.toRadians(x2)) + 
				Math.cos(Math.toRadians(x1)) * Math.cos(Math.toRadians(x2)) * Math.cos(Math.toRadians(y1 - y2)));
	}
	
}
