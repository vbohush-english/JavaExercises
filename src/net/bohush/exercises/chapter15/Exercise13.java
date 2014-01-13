package net.bohush.exercises.chapter15;

public class Exercise13 {

	public static void main(String[] args) throws CloneNotSupportedException {
		Octagon o1 = new Octagon(5);
		System.out.println("Aeea: " + o1.getArea());
		System.out.println("Perimeter: " + o1.getPerimeter());
		Octagon o2 = (Octagon)o1.clone();
		System.out.println(o1 == o2);
		System.out.println(o1.equals(o2));
		System.out.println(o1.compareTo(o2));
	}

}

class Octagon extends GeometricObject13 implements Cloneable, Comparable<Octagon>{
	double side;
	
	public Octagon() {
		super();
	}
	
	public Octagon(double side) {
		super();
		this.side = side;
	}
	
	public Octagon(String color, boolean filled, double side) {
		super(color, filled);
		this.side = side;
	}
	
	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nside = " + side;
	}
	
	@Override
	public double getArea() {		
		return (2 + 4 / Math.sqrt(2)) * side * side	;
	}

	@Override
	public double getPerimeter() {
		return 8 * side;
	}

	@Override
	public int compareTo(Octagon o) {
		if (getArea() > o.getArea()) {
			return 1;
		} else if (getArea() < o.getArea()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Octagon) {
			return getArea() == ((Octagon)obj).getArea();
		} else {
			return false;
		}
	}
}


abstract class GeometricObject13 {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;

	/** Construct a default geometric object */
	protected GeometricObject13() {
		dateCreated = new java.util.Date();
	}

	/** Construct a geometric object with color and filled value */
	protected GeometricObject13(String color, boolean filled) {
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