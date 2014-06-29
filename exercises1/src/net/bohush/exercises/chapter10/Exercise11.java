package net.bohush.exercises.chapter10;

public class Exercise11 {

	public static void main(String[] args) {
		Circle2D c1 = new Circle2D(2, 2, 5.5);
		System.out.println("Area = " + c1.getArea());
		System.out.println("Perimeter = " + c1.getPerimeter	());
		System.out.println(c1.contains(3, 3));
		System.out.println(c1.contains(new Circle2D(4, 5, 10.5)));
		System.out.println(c1.contains(new Circle2D(4, 5, 1.0)));
		System.out.println(c1.overlaps(new Circle2D(3, 5, 2.3)));

	}

}

class Circle2D {
	private double x;
	private double y;
	private double radius;
	
	public Circle2D(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public Circle2D() {
		this(0, 0, 1.0);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getArea() {
		return radius * radius * Math.PI;
	}
	
	public double getPerimeter() {
		return 2 * radius * Math.PI;
	}
		
	public boolean contains(double x2, double y2) {
		double distance = Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
		if (distance > radius) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean contains(Circle2D circle) {
		double distance = Math.sqrt((circle.getX() - x) * (circle.getX() - x) + (circle.getY() - y) * (circle.getY() - y));
		if (distance <= radius - circle.getRadius()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean overlaps(Circle2D circle) {
		double distance = Math.sqrt((circle.getX() - x) * (circle.getX() - x) + (circle.getY() - y) * (circle.getY() - y));
		if (distance <= (circle.getRadius() + radius)) {
			return true;
		} else {
			return false;
		}
	}
	
}