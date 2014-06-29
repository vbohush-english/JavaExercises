package net.bohush.exercises.chapter08;

import java.util.Scanner;

public class Exercise13 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of rows and columns in the array: ");
		int rows = input.nextInt();
		int columns = input.nextInt();
		System.out.println("Enter the array:");
		double[][] a = new double[rows][columns];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = input.nextDouble();
			}
		}
		Location location = Location.locateLargest(a);
		System.out.print("The location of the largest element is " + location.maxValue + " at (" + location.row + ", " + location.column + ")");
	}

}

class Location {
	public int row;
	public int column;
	public double maxValue;
	
	public static Location locateLargest(double[][] a) {
		Location location = new Location();
		location.maxValue = a[0][0];
		location.row = 0;
		location.column = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] > location.maxValue) {
					location.maxValue = a[i][j];
					location.row = i;
					location.column = j;	
				}
			}
		}
		return location;
	}
}
