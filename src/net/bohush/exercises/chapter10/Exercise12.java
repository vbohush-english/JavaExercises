package net.bohush.exercises.chapter10;

import java.awt.geom.Line2D;

public class Exercise12 {

	public static void main(String[] args) {
		Triangle2D t1 = new Triangle2D(new MyPoint(2.5, 2), new MyPoint(4.2, 3), new MyPoint(5, 3.5));
		System.out.println("Area = " + t1.getArea());
		System.out.println("Perimeter = " + t1.getPerimeter	());
		System.out.println(t1.contains(new MyPoint(3, 3)));
		System.out.println(t1.contains(new Triangle2D(new MyPoint(2.9, 2), new MyPoint(4, 1), new MyPoint(1, 3.4))));
		System.out.println(t1.overlaps(new Triangle2D(new MyPoint(2, 5.5), new MyPoint(4, -3), new MyPoint(2, 6.5))));		
	}

}


class Triangle2D {
	private MyPoint p1;
	private MyPoint p2;
	private MyPoint p3;
	
	public MyPoint getP1() {
		return p1;
	}
	
	public MyPoint getP2() {
		return p2;
	}
	
	public MyPoint getP3() {
		return p3;
	}
	
	public void setP1(MyPoint p1) {		
		this.p1 = p1;	
	}
	
	public void setP2(MyPoint p2) {		
		this.p2 = p2;	
	}
	
	public void setP3(MyPoint p3) {		
		this.p3 = p3;	
	}
	
	public Triangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	public Triangle2D(double p1x, double p1y, double p2x, double p2y, double p3x, double p3y) {
		this(new MyPoint(p1x, p1y), new MyPoint(p2x, p2y), new MyPoint(p3x, p3y));
	}
	
	public Triangle2D() {
		this(0, 0, 1, 1, 2, 5);
	}
	
	private double getSide1() {
		return p2.distance(p3);
	}
	
	private double getSide2() {
		return p1.distance(p3);
	}
	
	private double getSide3() {
		return p1.distance(p2);
	}
	
	public double getPerimeter() {
		return getSide1() + getSide2() + getSide3();
	}
	
	public double getArea() {
		double s = (getSide1() + getSide2() + getSide3()) / 2;
		return Math.sqrt(s * (s - getSide1()) * (s - getSide2()) * (s - getSide3()));
	}
	

		
	public boolean contains(MyPoint p) {
		
		Line2D.Double dashedLine1 = new Line2D.Double(p.getX(), p.getY(), p1.getX(), p1.getY());
		Line2D.Double dashedLine2 = new Line2D.Double(p.getX(), p.getY(), p2.getX(), p2.getY());
		Line2D.Double dashedLine3 = new Line2D.Double(p.getX(), p.getY(), p3.getX(), p3.getY());
		Line2D.Double line1 = new Line2D.Double(p2.getX(), p2.getY(), p3.getX(), p3.getY());
		Line2D.Double line2 = new Line2D.Double(p1.getX(), p1.getY(), p3.getX(), p3.getY());
		Line2D.Double line3 = new Line2D.Double(p2.getX(), p2.getY(), p1.getX(), p1.getY());
		
		if (dashedLine1.intersectsLine(line1)) {
			return false;
		}
		if (dashedLine2.intersectsLine(line2)) {
			return false;
		}
		if (dashedLine3.intersectsLine(line3)) {
			return false;
		}
		
		double maxX = Triangle2D.getMax(p1.getX(), p2.getX(), p3.getX());
		double maxY = Triangle2D.getMax(p1.getY(), p2.getY(), p3.getY());
		double minX = Triangle2D.getMin(p1.getX(), p2.getX(), p3.getX());
		double minY = Triangle2D.getMin(p1.getY(), p2.getY(), p3.getY());
		if ((p.getX() > maxX) || (p.getX() < minX) || (p.getY() > maxY) || (p.getY() < minY)) {
			return false;
		}
		return true;
	}
	
	private static double getMax(double n1, double n2, double n3) {
		double max;
		if (n1 > n2) {
			max = n1;
		} else {
			max = n2;
		}
		if (n3 > max) {
			max = n3;
		}
		return max;
	}	
	
	private static double getMin(double n1, double n2, double n3) {
		double min;
		if (n1 < n2) {
			min = n1;
		} else {
			min = n2;
		}
		if (n3 < min) {
			min = n3;
		}
		return min;
	}
	
	
	public boolean contains(Triangle2D t) {
		if ((contains(t.getP1()) && contains(t.getP2()) && contains(t.getP3())) ||
			(t.contains(p1) && t.contains(p2) && t.contains(p3))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean overlaps(Triangle2D t) {
		if (contains(t)) {
			return true;
		}
		Line2D.Double line1 = new Line2D.Double(p2.getX(), p2.getY(), p3.getX(), p3.getY());
		Line2D.Double line2 = new Line2D.Double(p1.getX(), p1.getY(), p3.getX(), p3.getY());
		Line2D.Double line3 = new Line2D.Double(p2.getX(), p2.getY(), p1.getX(), p1.getY());

		Line2D.Double tLine1 = new Line2D.Double(t.getP2().getX(), t.getP2().getY(), t.getP3().getX(), t.getP3().getY());
		Line2D.Double tLine2 = new Line2D.Double(t.getP1().getX(), t.getP1().getY(), t.getP3().getX(), t.getP3().getY());
		Line2D.Double tLine3 = new Line2D.Double(t.getP2().getX(), t.getP2().getY(), t.getP1().getX(), t.getP1().getY());

		if (line1.intersectsLine(tLine1)
				|| line1.intersectsLine(tLine2)
				|| line1.intersectsLine(tLine3)
				|| line2.intersectsLine(tLine1)
				|| line2.intersectsLine(tLine2)
				|| line2.intersectsLine(tLine3)
				|| line3.intersectsLine(tLine1)
				|| line3.intersectsLine(tLine2)
				|| line3.intersectsLine(tLine3)) {
			return true;
		} else {
			return false;
		}
	}
	
}