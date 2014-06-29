package net.bohush.exercises.chapter15;

public class Exercise11 {

	public static void main(String[] args) {
		Circle11 c1 = new Circle11(7);
		Circle11 c2 = new Circle11(8);
		Circle11 c3 = new Circle11(7);
		System.out.println(c1.equals(c2));
		System.out.println(c1.equals(c3));
	}

}

class Circle11 extends GeometricObject11  implements Comparable<Circle11>  {
	private double radius;

	public Circle11() {
	}

	public Circle11(double radius) {
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

	@Override
	public int compareTo(Circle11 o) {
		if (getArea() > o.getArea()) {
			return 1;
		} else if (getArea() < o.getArea()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Circle11) {
			return radius == ((Circle11)obj).radius;			
		} else {
			return false;
		}
	}
}

abstract class GeometricObject11 {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;

	/** Construct a default geometric object */
	protected GeometricObject11() {
		dateCreated = new java.util.Date();
	}

	/** Construct a geometric object with color and filled value */
	protected GeometricObject11(String color, boolean filled) {
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
}