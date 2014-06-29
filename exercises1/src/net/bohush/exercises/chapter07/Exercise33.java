package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise33 {

	public static void main(String[] args) {
		System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
		Scanner input = new Scanner(System.in);
		double[][] points = new double[4][2];
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[i].length; j++) {
				points[i][j] = input.nextDouble();
			}
		}
		input.close();
		double[] intersectingPoint = Exercise31.getIntersectingPoint(new double[][]{points[0], points[2], points[1], points[3]});
		
		double[] areas = new double[4];
		areas[0] = Exercise32.getTriangleArea(new double[][]{intersectingPoint, points[0], points[1]});
		areas[1] = Exercise32.getTriangleArea(new double[][]{intersectingPoint, points[1], points[2]});
		areas[2] = Exercise32.getTriangleArea(new double[][]{intersectingPoint, points[2], points[3]});
		areas[3] = Exercise32.getTriangleArea(new double[][]{intersectingPoint, points[3], points[0]});
		java.util.Arrays.sort(areas);
		System.out.print("The areas are ");
		double sum = 0;
		for (int i = 0; i < areas.length; i++) {
			System.out.print(areas[i] + " ");
			sum += areas[i];
		}
		System.out.print(sum);
		
	}
	
	

}