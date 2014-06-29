package net.bohush.exercises.chapter24;

import java.util.ArrayList;

public class Exercise09 {

	public static void main(String[] args) {
		double[][] points = new double[6][2];
		for (int i = 0; i < points.length; i++) {
			points[i][0] = (int)(Math.random() * 100);
			points[i][1] = (int)(Math.random() * 100);
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
	}

	public static ArrayList<MyPoint> getConvexHull(double[][] s) {
		ArrayList<MyPoint> oldPoints = new ArrayList<>();
		for (int i = 0; i < s.length; i++) {
			oldPoints.add(new MyPoint(s[i][0], s[i][1]));
		}
		
		ArrayList<MyPoint> points = new ArrayList<>();
		
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
		points.add(h0);
		
		//second point
		MyPoint virtualPoint = new MyPoint(h0.x + 1, h0.y);
		MyPoint h1 = oldPoints.get(0);
		double tmpAngle = getAngle(h0, virtualPoint, h1);
		if(Double.isNaN(tmpAngle)) {
			tmpAngle = Double.MAX_VALUE;
		}
		for (int j = 1; j < oldPoints.size(); j++) {
			double newAngle = getAngle(h0, virtualPoint, oldPoints.get(j));
			if(Double.isNaN(newAngle)) {
				newAngle = Double.MAX_VALUE;
			}
			if(newAngle < tmpAngle) {
				tmpAngle = newAngle;
				h1 = oldPoints.get(j);
			}
		}
		points.add(h1);
		
		//other points
		while(h1 != points.get(0)) {
			MyPoint tmpPoint = oldPoints.get(0);
			double tmpAngle2 = getAngle(h1, tmpPoint, h0);
			if(Double.isNaN(tmpAngle2)) {
				tmpAngle2 = 0;
			}
			for (int j = 1; j < oldPoints.size(); j++) {
				double newAngle = getAngle(h1, oldPoints.get(j), h0);
				if(Double.isNaN(newAngle)) {
					continue;
				}
				if(newAngle > tmpAngle2) {
					tmpAngle2 = newAngle;
					tmpPoint = oldPoints.get(j);
				} else if(newAngle == tmpAngle2) {
					if(getSide(h1, oldPoints.get(j)) > getSide(h1, tmpPoint)) {
						tmpAngle2 = newAngle;
						tmpPoint = oldPoints.get(j);
					}
				}
			}
			if(tmpPoint != points.get(0)) {
				points.add(tmpPoint);
			}
			h0 = h1;
			h1 = tmpPoint;
		}
		return points;
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
	
	static class MyPoint {		
		double x, y;		
		MyPoint(double x, double y) {
			this.x = x; this.y = y;
		}
	}
}
