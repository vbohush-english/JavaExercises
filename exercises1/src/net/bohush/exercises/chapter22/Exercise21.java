package net.bohush.exercises.chapter22;

import java.util.Comparator;

public class Exercise21 {

	public static void main(String[] args) {
		GeometricObject21[] list = {new Circle21(5), new Rectangle21(4, 5),
				new Circle21(5.5), new Rectangle21(2.4, 5), new Circle21(0.5),
				new Rectangle21(4, 65), new Circle21(4.5), new Rectangle21(4.4, 1),
				new Circle21(6.5), new Rectangle21(4, 5)};
		selectionSort(list, new GeometricObjectComparator21());
		System.out.println(java.util.Arrays.toString(list));
	}
	
	public static <E> void selectionSort(E[] list, Comparator<? super E> comparator) {
		for (int i = 0; i < list.length - 1; i++) {
			E min = list[i + 0];
			for (int j = i + 1; j < list.length; j++) {
				if(comparator.compare(min, list[j]) > 0) {
					E tmp = min;
					min = list[j];
					list[j] = tmp;
				}
			}
			if(min != list[i]) {
				E tmp = min;
				min = list[i];
				list[i] = tmp;
			}
		}
	}

	
}

class GeometricObjectComparator21 implements Comparator<GeometricObject21>, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public int compare(GeometricObject21 o1, GeometricObject21 o2) {
		double area1 = o1.getArea();
		double area2 = o2.getArea();

		if (area1 < area2)
			return -1;
		else if (area1 == area2)
			return 0;
		else
			return 1;
	}
}

class Rectangle21 extends GeometricObject21 {
	private double width;
	private double height;

	public Rectangle21() {
	}

	public Rectangle21(double width, double height) {
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
		return super.toString() + "width is " + width + "\nheight is " + height;
	}
}

class Circle21 extends GeometricObject21 {
	private double radius;

	public Circle21() {
	}

	public Circle21(double radius) {
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
		return super.toString() + "radius is " + radius;
	}
}

abstract class GeometricObject21 {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;

	/** Construct a default geometric object */
	protected GeometricObject21() {
		dateCreated = new java.util.Date();
	}

	/** Construct a geometric object with color and filled value */
	protected GeometricObject21(String color, boolean filled) {
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
		return "\n\ncreated on " + dateCreated + "\ncolor: " + color
				+ " and filled: " + filled + "\narea is " + getArea() + "\n";
	}

	/** Abstract method getArea */
	public abstract double getArea();

	/** Abstract method getPerimeter */
	public abstract double getPerimeter();

}
