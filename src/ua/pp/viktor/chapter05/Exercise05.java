package ua.pp.viktor.chapter05;

import java.util.Scanner;

public class Exercise05 {

	public static void main(String[] args) {
		System.out.print("Enter three numbers: ");
		Scanner input = new Scanner(System.in);
		double num1 = input.nextDouble();
		double num2 = input.nextDouble();
		double num3 = input.nextDouble();
		input.close();
		System.out.print("Sorted numbers: ");
		displaySortedNumbers(num1, num2, num3);
	}
	
	public static void displaySortedNumbers(double num1, double num2, double num3) {
		if (num1 > num2) {
			double tmp = num2;
			num2 = num1;
			num1 = tmp;
		}
		if (num3 < num2) {
			double tmp = num2;
			num2 = num3;
			num3 = tmp;
		}
		if (num1 > num2) {
			double tmp = num2;
			num2 = num1;
			num1 = tmp;
		}
		System.out.println(num1 + ", " + num2 + ", " + num3);
	}

}
