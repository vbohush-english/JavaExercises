package net.bohush.exercises.chapter08;

import java.util.Scanner;

public class Exercise10 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a, b, c: ");
		double a = input.nextDouble();
		double b = input.nextDouble();
		double c = input.nextDouble();
		QuadraticEquation quadraticEquation = new QuadraticEquation(a, b, c);
		if (quadraticEquation.getDiscriminant() > 0) {
			System.out.println("The roots is " + quadraticEquation.getRoot1() + ", " + quadraticEquation.getRoot2());
		} else if (quadraticEquation.getDiscriminant() == 0) {
			System.out.println("The root is " + quadraticEquation.getRoot1());
		} else {
			System.out.println("The equation has no real roots");
		}
	}

}

class QuadraticEquation {
	private double a;
	private double b;
	private double c;
	
	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double getC() {
		return c;
	}
	
	public QuadraticEquation(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;		
	}
	
	public double getDiscriminant() {
		return b * b - 4 * a * c;
	}
	
	public double getRoot1() {
		if (getDiscriminant() >= 0) {
			return (- b + Math.sqrt(getDiscriminant())) / (2 * a);
		} else {
			return 0;
		}
	}
	
	public double getRoot2() {
		if (getDiscriminant() >= 0) {
			return (- b - Math.sqrt(getDiscriminant())) / (2 * a);
		} else {
			return 0;
		}
	}
	
}