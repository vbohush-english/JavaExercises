package net.bohush.exercises.chapter10;

public class Exercise04 {

	public static void main(String[] args) {
		MyPoint point = new MyPoint(1, 1);
		System.out.println(point.distance(new MyPoint(2, 2)));
		System.out.println(point.distance(2, 2));
		System.out.println(new MyPoint(0, 0).distance(10, 30.5));
	}

}

class MyPoint {
	private double x;
	private double y;
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public MyPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public MyPoint() {
		this(0, 0);
	}
	
	public double distance(MyPoint point) {
		return Math.sqrt((point.x - x) * (point.x - x) + (point.y - y) * (point.y - y));
	}
	
	public double distance(double x, double y) {
		return distance(new MyPoint(x, y));
	}
}