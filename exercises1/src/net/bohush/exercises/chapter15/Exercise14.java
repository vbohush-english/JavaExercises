package net.bohush.exercises.chapter15;

public class Exercise14 {

	public static void main(String[] args) {
		GeometricObject14[] a = new GeometricObject14[4];
		a[0] = new Rectangle14(4, 5);
		a[1] = new Rectangle14(6, 3);
		a[2] = new Circle14(6);
		a[3] = new Circle14(3);
		System.out.println(sumArea(a));
	}

	public static double sumArea(GeometricObject14[] a) {
		double result = 0;
		for (int i = 0; i < a.length; i++) {
			result += a[i].getArea();
		}
		return result;
	}
}


class Rectangle14 extends GeometricObject14 {
	private double width;
	private double height;

	public Rectangle14() {
	}

	public Rectangle14(double width, double height) {
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

class Circle14 extends GeometricObject14 {
	private double radius;

	public Circle14() {
	}

	public Circle14(double radius) {
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

abstract class GeometricObject14 implements Comparable<GeometricObject14> {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;

	/** Construct a default geometric object */
	protected GeometricObject14() {
		dateCreated = new java.util.Date();
	}

	/** Construct a geometric object with color and filled value */
	protected GeometricObject14(String color, boolean filled) {
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

	public static GeometricObject14 max(GeometricObject14 o1, GeometricObject14 o2) {
		if (o1.compareTo(o2) > 0) {
			return o1;
		} else {
			return o2;
		}
	}

	@Override
	public int compareTo(GeometricObject14 o) {
		if (getArea() > o.getArea()) {
			return 1;
		} else if (getArea() < o.getArea()) {
			return -1;
		} else {
			return 0;
		}
	}
}