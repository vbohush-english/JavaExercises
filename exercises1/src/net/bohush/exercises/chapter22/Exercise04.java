package net.bohush.exercises.chapter22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Exercise04 {

	public static void main(String[] args) {
		ArrayList<Point> points = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			points.add(new Point((int)(Math.random() * 100), (int)(Math.random() * 100)));
		}
		System.out.println("\n\nSorted by X:");
		Collections.sort(points);
		for (Point point : points) {
			System.out.println(point);
		}
		System.out.println("\n\nSorted by Y:");
		Collections.sort(points, new CompareY());
		for (Point point : points) {
			System.out.println(point);
		}
	}
	
}

class Point implements Comparable<Point> {
	private int x;
	private int y;		
	
	public Point() {
		this(0, 0);
	}	
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if (x > o.x) {
			return 1;
		} else if (x < o.x) {
			return -1;
		} else {
			if (y > o.y) {
				return 1;
			} else if (y < o.y) {
				return -1;
			} else {
				return 0;
			}
		}
	}		
	
	@Override
	public String toString() {
		return "x: " + x + ",\ty: " + y;
	}
}

class CompareY implements Comparator<Point> {
	
	@Override
	public int compare(Point o1, Point o2) {
		if (o1.getY() > o2.getY()) {
			return 1;
		} else if (o1.getY() < o2.getY()) {
			return -1;
		} else {
			if (o1.getX() > o2.getX()) {
				return 1;
			} else if (o1.getX() < o2.getX()) {
				return -1;
			} else {
				return 0;
			}
		}
	}	

}
