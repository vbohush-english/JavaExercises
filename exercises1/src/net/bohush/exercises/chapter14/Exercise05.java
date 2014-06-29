package net.bohush.exercises.chapter14;

import java.util.Scanner;

public class Exercise05 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter three sides of triangle: ");
		double side1 = input.nextDouble();
		double side2 = input.nextDouble();
		double side3 = input.nextDouble();
		System.out.print("Enter color of triangle: ");
		String color = input.next();
		System.out.print("Fill triangle(y/n)? ");
		boolean filled = (input.next().equals("y") ? true : false);
		Triangle t1;
		try {
			t1 = new Triangle(side1, side2, side3, color, filled);
			System.out.println(t1);
		} catch (IllegalTriangleException e) {
			System.out.println(e);
		}
		
	}

}

class IllegalTriangleException extends Exception {
	double bigSide;
	double littleSide1;
	double littleSide2;

	private static final long serialVersionUID = 1L;

	public IllegalTriangleException(double bigSide, double littleSide1, double littleSide2) {
		this.bigSide = bigSide;
		this.littleSide1 = littleSide1;
		this.littleSide2 = littleSide2;
	}
	
	@Override
	public String getMessage() {
		return "side(" + bigSide + ") is bigger than 2 other sides(" + littleSide1 + " + " + littleSide2 + ")";
	}
}

class Triangle extends SimpleGeometricObject {
	private double side1;
	private double side2;
	private double side3;


	public Triangle(double side1, double side2, double side3) throws IllegalTriangleException {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
		checkTriangle();
	}

	public Triangle(double side1, double side2, double side3, String color, boolean filled) throws IllegalTriangleException {
		super(color, filled);
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
		checkTriangle();
	}

	private void checkTriangle() throws IllegalTriangleException {
		
		if (side1 + side2 <= side3) { 
			throw new IllegalTriangleException(side3, side1, side2);
		}
		
		if (side1 + side3 <= side2) { 
			throw new IllegalTriangleException(side2, side1, side3);
		}
		
		if (side3 + side2 <= side1) { 
			throw new IllegalTriangleException(side1, side3, side2);
		}
	}
	
	public Triangle() throws IllegalTriangleException  {
		this(1, 1, 1);
	}
	
	public double getSide1() {
		return side1;
	}
	
	public double getSide2() {
		return side2;
	}
	
	public double getSide3() {
		return side3;
	}
	
	public double getArea() {
		double s = (side1 + side2 + side3) / 2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}


	public double getPerimeter() {
		return side1 + side2 + side3;
	}

	public String toString() {
		return super.toString() + "\nTriangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
	}
	
}


class SimpleGeometricObject {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;

	/** Construct a default geometric object */
	public SimpleGeometricObject() {
		dateCreated = new java.util.Date();
	}

	/**
	 * Construct a geometric object with the specified color and filled value
	 */
	public SimpleGeometricObject(String color, boolean filled) {
		dateCreated = new java.util.Date();
		this.color = color;
		this.filled = filled;
	}

	/** Return color */
	public String getColor() {
		return color;
	}

	/** Set a new color */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Return filled. Since filled is boolean, its get method is named isFilled
	 */
	public boolean isFilled() {
		return filled;
	}

	/** Set a new filled */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	/** Get dateCreated */
	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	/** Return a string representation of this object */
	public String toString() {
		return "created on " + dateCreated + "\ncolor: " + color
				+ " and filled: " + filled;
	}
}