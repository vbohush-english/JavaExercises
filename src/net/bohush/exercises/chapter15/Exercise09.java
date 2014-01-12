package net.bohush.exercises.chapter15;

public class Exercise09 {

	public static void main(String[] args) {
		GeometricObject09[] objects = new GeometricObject09[5];
		objects[0] = new Circle09(5);
		objects[1] = new Circle09(1);
		objects[2] = new Square(8);
		objects[3] = new Square(10);
		objects[4] = new Circle09(6);
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] instanceof Colorable) {
				((Colorable) (objects[i])).howToColor();
			}
		}
	}

}

class Square extends GeometricObject09 implements Colorable {
	private double side;

	public Square() {
	}

	public Square(double side) {
		this.side = side;
	}

	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}

	@Override
	public double getArea() {
		return side * side;
	}

	@Override
	public double getPerimeter() {
		return 2 * side;
	}

	@Override
	public String toString() {
		return super.toString() + "\nside is " + side;
	}

	@Override
	public void howToColor() {
		System.out.println("Color all four sides");
	}
}

interface Colorable {
	void howToColor();
}

abstract class GeometricObject09 {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;

	/** Construct a default geometric object */
	protected GeometricObject09() {
		dateCreated = new java.util.Date();
	}

	/** Construct a geometric object with color and filled value */
	protected GeometricObject09(String color, boolean filled) {
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

class Circle09 extends GeometricObject09 {
	private double radius;

	public Circle09() {
	}

	public Circle09(double radius) {
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
}
