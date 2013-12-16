package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		System.out.print("Enter ten numbers: ");
		double[] array = new double[10];
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < array.length; i++) {
			array[i] = input.nextDouble();
		}
		input.close();
		System.out.println("The minimum number is: " + min(array));
	}
	
	public static double min(double[] array) {
		double min = array[0];
		for (double data:array) {
			if (data < min) {
				min = data;
			}
		}
		return min;		
	}

}