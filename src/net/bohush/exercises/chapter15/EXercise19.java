package net.bohush.exercises.chapter15;

import java.util.Scanner;

public class EXercise19 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the first complex number: ");
		Complex c1 = new Complex(input.nextDouble(), input.nextDouble());
		System.out.print("Enter the second complex number: ");
		Complex c2 = new Complex(input.nextDouble(), input.nextDouble());
		System.out.println(c1 + " + " + c2 + " = " + c1.add(c2));
		System.out.println(c1 + " - " + c2 + " = " + c1.subtract(c2));
		System.out.println(c1 + " * " + c2 + " = " + c1.multiply(c2));
		System.out.println(c1 + " / " + c2 + " = " + c1.divide(c2));
		System.out.println("|" + c1 + "| = " + c1.abs());
	}

}

class Complex {
	private double a;
	private double b;
	
	public double getRealPart() {
		return a;
	}
	public void setRealPart(double a) {
		this.a = a;
	}
	public double getImaginaryPart() {
		return b;
	}
	public void setImaginaryPart(double b) {
		this.b = b;
	}
	
	public Complex(double a, double b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	public Complex(double a) {
		this(a, 0);
	}	
	
	public Complex() {
		this(0, 0);
	}
	
	
	public Complex add(Complex c2) {
		return new Complex(a + c2.a, b + c2.b);
	}
	
	public Complex subtract(Complex c2) {
		return new Complex(a - c2.a, b - c2.b);
	}
	
	public Complex multiply(Complex c2) {
		return new Complex(a * c2.a - b * c2.b, b * c2.a + a * c2.b);
	}
	
	public Complex divide(Complex c2) {
		return new Complex((a * c2.a + b * c2.b) / (c2.a * c2.a + c2.b * c2.b), (b * c2.a - a * c2.b) / (c2.a * c2.a + c2.b * c2.b));
	}
	
	public double abs() {
		return Math.sqrt(a * a + b * b);
	}
	@Override
	public String toString() {
		return "(" + a + " + " + b + "i)";
	}	
	
}