package net.bohush.exercises.chapter15;

public class Exercise07 {

	public static void main(String[] args) {
		GeometricObject07 r1 = new Rectangle07(100, 200);
		GeometricObject07 r2 = new Rectangle07(120, 180);
		GeometricObject07 c1 = new Circle07(5);
		GeometricObject07 c2 = new Circle07(6);
		System.out.println(GeometricObject07.max(r1, r2));
		System.out.println(GeometricObject07.max(c1, c2));
	}

}

class Rectangle07 extends GeometricObject07 {
	private double width;
	private double height;

	public Rectangle07() {
	}

	public Rectangle07(double width, double height) {
		this.width = width;
		this.height = height;
	}

	/** Return width */
	public double getWidth() {
		return width;
	}

	/** Set a new width */
	public void setWidth(double width) {
		this.width = width;
	}

	/** Return height */
	public double getHeight() {
		return height;
	}

	/** Set a new height */
	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	/** Return area */
	public double getArea() {
		return width * height;
	}

	@Override
	/** Return perimeter */
	public double getPerimeter() {
		return 2 * (width + height);
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nwidth is " + width + "\nheight is " + height;
	}
}

class Circle07 extends GeometricObject07 {
	private double radius;

	public Circle07() {
	}

	public Circle07(double radius) {
		this.radius = radius;
	}

	/** Return radius */
	public double getRadius() {
		return radius;
	}

	/** Set a new radius */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	/** Return area */
	public double getArea() {
		return radius * radius * Math.PI;
	}

	/** Return diameter */
	public double getDiameter() {
		return 2 * radius;
	}

	@Override
	/** Return perimeter */
	public double getPerimeter() {
		return 2 * radius * Math.PI;
	}

	/* Print the circle info */
	public void printCircle() {
		System.out.println("The circle is created " + getDateCreated()
				+ " and the radius is " + radius);
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nradius is " + radius;
	}
}

abstract class GeometricObject07 implements Comparable<GeometricObject07> {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;

	/** Construct a default geometric object */
	protected GeometricObject07() {
		dateCreated = new java.util.Date();
	}

	/** Construct a geometric object with color and filled value */
	protected GeometricObject07(String color, boolean filled) {
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
	 * Return filled. Since filled is boolean, the get method is named isFilled
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

	@Override
	public String toString() {
		return "created on " + dateCreated + "\ncolor: " + color
				+ " and filled: " + filled;
	}

	/** Abstract method getArea */
	public abstract double getArea();

	/** Abstract method getPerimeter */
	public abstract double getPerimeter();

	public static GeometricObject07 max(GeometricObject07 o1, GeometricObject07 o2) {
		if (o1.compareTo(o2) > 0) {
			return o1;
		} else {
			return o2;
		}
	}

	@Override
	public int compareTo(GeometricObject07 o) {
		if (getArea() > o.getArea()) {
			return 1;
		} else if (getArea() < o.getArea()) {
			return -1;
		} else {
			return 0;
		}
	}
}