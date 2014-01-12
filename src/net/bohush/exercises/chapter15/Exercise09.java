package net.bohush.exercises.chapter15;

public class Exercise09 {

	public static void main(String[] args) {
		GeometricObject[] objects = new GeometricObject[5];
		objects[0] = new Circle2(5);
		objects[1] = new Circle2(1);
		objects[2] = new Square(8);
		objects[3] = new Square(10);
		objects[4] = new Circle2(6);
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] instanceof Colorable) {
				((Colorable)(objects[i])).howToColor();
			}
		}
	}

}

class Square extends GeometricObject implements Colorable {
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
