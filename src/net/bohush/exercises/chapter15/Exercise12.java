package net.bohush.exercises.chapter15;

public class Exercise12 {

	public static void main(String[] args) {
		Rectangle12 c1 = new Rectangle12(3, 4);
		Rectangle12 c2 = new Rectangle12(5, 6);
		Rectangle12 c3 = new Rectangle12(2, 6);
		System.out.println(c1.equals(c2));
		System.out.println(c1.equals(c3));
	}

}


class Rectangle12 extends GeometricObject12 implements Comparable<Rectangle12> {
	private double width;
	private double height;

	public Rectangle12() {
	}

	public Rectangle12(double width, double height) {
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

	@Override
	public int compareTo(Rectangle12 o) {
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
		if (obj instanceof Rectangle12) {
			return getArea() == ((Rectangle12)obj).getArea(); 
		} else {
			return false;
		}
	}
}


abstract class GeometricObject12 {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;

	/** Construct a default geometric object */
	protected GeometricObject12() {
		dateCreated = new java.util.Date();
	}

	/** Construct a geometric object with color and filled value */
	protected GeometricObject12(String color, boolean filled) {
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