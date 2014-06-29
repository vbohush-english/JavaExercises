package net.bohush.exercises.chapter03;

import java.util.Scanner;

public class Exercise01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a, b, c: ");
		double a = input.nextDouble();
		double b = input.nextDouble();
		double c = input.nextDouble();
		input.close();
		double D = b * b - 4 * a * c;
		if (D > 0) {
			double x1 = (- b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
			double x2 = (- b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
			System.out.println("The roots is " + x1 + ", " + x2);
		} else if (D == 0) {
			double x = - (b / (2 * a));
			System.out.println("The root is " + x);
		} else {
			System.out.println("The equation has no real roots");
		}

	}

}
