package net.bohush.exercises.chapter16;

import java.awt.Point;
import java.util.ArrayList;


public class Exercise30 {

	public static void main(String[] args) {
		for (int i = 10; i <= 80; i++) {
			System.out.println("For a lattice of size " + i + ", the probability of dead-end paths is " + getPercent(i) + "%");	
		}
		
	}
	
	
	static public double getPercent(int count) {
		int exitCount = 0;
		for (int j = 0; j < 10000; j++) {
			ArrayList<Point> walkPoints = new ArrayList<>();
			Point nextPoit = new Point(count / 2, count / 2);
			walkPoints.add(nextPoit);
			boolean allowNextPoint = true;
			
			while (allowNextPoint) {
				
				Point[] tmpPoint = new Point[4];
				tmpPoint[0] = new Point(nextPoit.x - 1, nextPoit.y);
				tmpPoint[1] = new Point(nextPoit.x + 1, nextPoit.y);
				tmpPoint[2] = new Point(nextPoit.x, nextPoit.y + 1);
				tmpPoint[3] = new Point(nextPoit.x, nextPoit.y - 1);

				for (int i = 0; i < tmpPoint.length; i++) {
					int index = (int) (Math.random() * tmpPoint.length);
					Point temp = tmpPoint[i];
					tmpPoint[i] = tmpPoint[index];
					tmpPoint[index] = temp;
				}
				
				boolean allowNext = false;
				for (int i = 0; i < tmpPoint.length; i++) {
					if (!walkPoints.contains(tmpPoint[i])) {
						walkPoints.add(tmpPoint[i]);
						nextPoit = tmpPoint[i];
						allowNext = true;
						if ((nextPoit.x <= 0) || (nextPoit.x >= count) || (nextPoit.y <= 0) || (nextPoit.y >= count)) {
							exitCount++;
							allowNext = false;
						}
						break;							
					}
				}
				allowNextPoint = allowNext;
			}
		}	
		return (10000 - exitCount) / 100.0;
	}
}

