package ua.pp.viktor.chapter06;

import java.util.Scanner;

public class Exercise08 {

	public static void main(String[] args) {
		System.out.print("Enter ten numbers: ");
		double[] array = new double[10];
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < array.length; i++) {
			array[i] = input.nextDouble();
		}
		input.close();
		System.out.println("Average = " + average(array));
	}
	
	public static int average(int[] array) {
		int average = 0;
		for (int data:array) {
			average += data;
		}
		return average / array.length;
	}
	
	public static double average(double[] array) {
		double average = 0;
		for (double data:array) {
			average += data;
		}
		return average / array.length;		
	}

}
