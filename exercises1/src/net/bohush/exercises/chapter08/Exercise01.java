package net.bohush.exercises.chapter08;

public class Exercise01 {

	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(4, 40);
		Rectangle r2 = new Rectangle(3.5, 35.9);
		System.out.println("Width of r1: " + r1.width);
		System.out.println("Height of r1: " + r1.height);
		System.out.println("Area of r1: " + r1.getArea());
		System.out.println("Perimeter of r1: " + r1.getPerimeter());
		System.out.println("Width of r2: " + r2.width);
		System.out.println("Height of r2: " + r2.height);
		System.out.println("Area of r2: " + r2.getArea());
		System.out.println("Perimeter of r2: " + r2.getPerimeter());
	}

}

class Rectangle {
	double width = 1;
	double height = 1;
	
	Rectangle() {	
	}
	
	Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	double getArea() {
		return width * height;
	}
	
	double getPerimeter() {
		return 2 * (width + height);
	}
}