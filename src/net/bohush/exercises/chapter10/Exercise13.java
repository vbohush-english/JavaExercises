package net.bohush.exercises.chapter10;

public class Exercise13 {

	public static void main(String[] args) {
		MyRectangle2D r1  = new MyRectangle2D(2, 2, 5.5, 4.9);
		System.out.println("Area = " + r1.getArea());
		System.out.println("Perimeter = " + r1.getPerimeter	());
		System.out.println(r1.contains(3, 3));
		System.out.println(r1.contains(new MyRectangle2D(4, 5, 10.5, 3.2)));
		System.out.println(r1.overlaps(new MyRectangle2D(3, 5, 2.3, 5.4)));	
	}

}

class MyRectangle2D {
	private double x;
	private double y;
	private double width;
	private double height;
	
	public MyRectangle2D(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public MyRectangle2D() {
		this(0, 0, 1, 1);
	}
	
	public double getPerimeter() {
		return 2 * (width + height);
	}
	
	public double getArea() {
		return width * height;
	}
	
	public boolean contains(double x, double y) {
		double x1 = this.x - width / 2;
		double x2 = this.x + width / 2;
		double y1 = this.y - height / 2;
		double y2 = this.y + height / 2;
		if ((x >= x1) && (x <= x2) && (y >= y1) && (y <= y2)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contains(MyRectangle2D r) {
		if ((((this.width - r.width) / 2) >= Math.abs(this.x - r.x)) && (((this.height - r.height) / 2) >= Math.abs(this.y - r.y))) {
			return true;
		} else {
			return false;
		}
	}
	public boolean overlaps(MyRectangle2D r) {
		if ((((this.width + r.width) / 2) >= Math.abs(this.x - r.x)) && (((this.height + r.height) / 2) >= Math.abs(this.y - r.y))) {
			return true;
		} else {
			return false;
		}
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
}