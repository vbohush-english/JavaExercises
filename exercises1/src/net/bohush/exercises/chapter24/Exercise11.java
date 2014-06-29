package net.bohush.exercises.chapter24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

public class Exercise11 {

	public static void main(String[] args) {
		double[][] points = new double[100][2];
		for (int i = 0; i < points.length; i++) {
			points[i][0] = (int)(Math.random() * 1000);
			points[i][1] = (int)(Math.random() * 1000);
		}
		
		/*
		points[0][0] = 1;
		points[0][1] = 2.4;
		points[1][0] = 2.5;
		points[1][1] = 2;
		points[2][0] = 1.5;
		points[2][1] = 34.5;
		points[3][0] = 5.5;
		points[3][1] = 6;
		points[4][0] = 6;
		points[4][1] = 2.4;
		points[5][0] = 5.5;
		points[5][1] = 9;
		*/
		
		           
		ArrayList<MyPoint> hull = getConvexHull(points);
		System.out.println("There are " + points.length + " points:");
		for (int i = 0; i < points.length; i++) {
			System.out.print("(" + points[i][0] + ", " + points[i][1] + ")  ");
		}
		System.out.println("\nThe convex hull is:");
		for (MyPoint myPoint : hull) {
			System.out.print("(" + myPoint.x + ", " + myPoint.y + ")  ");
		}
		
		
		ArrayList<Exercise09.MyPoint> hull2 = Exercise09.getConvexHull(points);
		System.out.println("\nThe convex hull2 is:");
		for (Exercise09.MyPoint myPoint : hull2) {
			System.out.print("(" + myPoint.x + ", " + myPoint.y + ")  ");
		}
	}

	public static ArrayList<MyPoint> getConvexHull(double[][] s) {
		ArrayList<MyPoint> oldPoints = new ArrayList<>();
		for (int i = 0; i < s.length; i++) {
			oldPoints.add(new MyPoint(s[i][0], s[i][1]));
		}
		
		//first point
		MyPoint h0 = oldPoints.get(0);
		for (int i = 1; i < oldPoints.size(); i++) {
			if(oldPoints.get(i).y > h0.y) {
				h0 = oldPoints.get(i);
			} else if(oldPoints.get(i).y == h0.y) {
				if(oldPoints.get(i).x > h0.x) {
					h0 = oldPoints.get(i);
				}
			}
		}
		for (MyPoint myPoint : oldPoints) {
			myPoint.setRightMostLowestPoint(h0);
		}
		
		Collections.sort(oldPoints);		
		
		LinkedList<MyPoint> points = new LinkedList<>();
		points.add(oldPoints.get(0));
		points.add(oldPoints.get(1));
		points.add(oldPoints.get(2));
		
		
		int i = 3;
		int n = oldPoints.size();
		while (i < n) {
			MyPoint t1 = points.removeLast();
			MyPoint t2 = points.getLast();
			points.add(t1);
			MyPoint p =  oldPoints.get(i);
			if (isLeft(p, t1, t2)) {
				points.add(p);
				i++;
			} else {
				points.removeLast();
			}
		}
				
		return new ArrayList<MyPoint>(points);
	}
	
	public static boolean isLeft(MyPoint p0, MyPoint p1, MyPoint p2) {
		double position = (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
		if (position > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static double getAngle(MyPoint p1, MyPoint p2, MyPoint p3) {
		double a = getSide(p2, p3);
		double b = getSide(p1, p3);
		double c = getSide(p1, p2);
		return Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
	}
	
	public static double getSide(MyPoint p1, MyPoint p2) {
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
	}
	
	private static class MyPoint implements Comparable<MyPoint> {
		double x, y;
		MyPoint rightMostLowestPoint;

		MyPoint(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public void setRightMostLowestPoint(MyPoint p) {
			rightMostLowestPoint = p;
		}

		@Override
		public int compareTo(MyPoint o) {
			MyPoint virtualPoint = new MyPoint(rightMostLowestPoint.x + 1, rightMostLowestPoint.y);
			double a1 = getAngle(rightMostLowestPoint, virtualPoint, o);
			double a2 = getAngle(rightMostLowestPoint, virtualPoint, this);
			if(a1 > a2) {
				return -1;
			} else if(a2 > a1) {
				return 1;
			} else {
				double l1 = getSide(rightMostLowestPoint, o);
				double l2 = getSide(rightMostLowestPoint, this);
				if(l1 > l2) {
					return -1;
				} else if(l2 > l1) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
}
