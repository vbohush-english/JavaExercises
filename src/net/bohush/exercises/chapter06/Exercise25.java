package net.bohush.exercises.chapter06;

import java.util.Scanner;

public class Exercise25 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double[] eqn = new double[3];
		System.out.print("Enter a, b, c: ");
		eqn[0] = input.nextDouble();
		eqn[1] = input.nextDouble();
		eqn[2] = input.nextDouble();
		input.close();
		double[] roots = new double[2];
		int numberOfRoots = solveQuadratic(eqn, roots);
		if (numberOfRoots == 2) {
			System.out.println("The roots is " + roots[0] + ", " + roots[1]);
		} else if (numberOfRoots == 1) {
			System.out.println("The root is " + roots[0]);
		} else {
			System.out.println("The equation has no real roots");
		}
	}
	
	public static int solveQuadratic(double[] eqn, double[] roots) {
		double a = eqn[0];
		double b = eqn[1];
		double c = eqn[2];
		double D = b * b - 4 * a * c;
		if (D > 0) {
			roots[0] = (- b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
			roots[1] = (- b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
			return 2;
		} else if (D == 0) {
			roots[0] = - (b / (2 * a));
			return 1;
		} else {
			return 0;
		}
	}
	

}
