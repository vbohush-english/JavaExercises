package net.bohush.exercises.chapter15;

public class Exercise08 {

	public static void main(String[] args) {
		GeometricObject2 c1 = new Circle(5);
		GeometricObject2 c2 = new Circle(6);
		if (c1.compareTo(c2) > 0) {
			System.out.println(c1);
		} else {
			System.out.println(c2);
		}
	}

}

class ComparableCircle extends Circle2 implements Comparable<ComparableCircle> {
	@Override
	public int compareTo(ComparableCircle o) {
		if (getArea() > o.getArea()) {
			return 1;
		} else if (getArea() < o.getArea()) {
			return -1;
		} else {
			return 0;
		}
	}
}


class Circle2 extends GeometricObject {
	private double radius;

	public Circle2() {
	}

	public Circle2(double radius) {
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