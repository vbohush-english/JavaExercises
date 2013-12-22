package ua.pp.viktor.chapter07;

import java.util.Scanner;

public class Exercise21 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of cities: ");
		int numberOfCities = input.nextInt();
		double[][] cities = new double[numberOfCities][2];
		System.out.print("Enter the coordinates of the cities: ");
		for (int i = 0; i < cities.length; i++) {
			for (int j = 0; j < cities[i].length; j++) {
				cities[i][j] = input.nextDouble();
			}
		}
		double[] distances = new double[numberOfCities];
		int centralCity = 0;
		for (int i = 0; i < cities.length; i++) {
			for (int j = 0; j < cities.length; j++) {
				distances[i] += distance(cities[i][0], cities[i][1], cities[j][0], cities[j][1]);
			}
			if (distances[i] < distances[centralCity]) {
				centralCity = i;
			}
		}
		System.out.println("The central city is at (" + cities[centralCity][0] + ", " + cities[centralCity][1] + ")");
	}
	
	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

}
