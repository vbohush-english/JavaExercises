package net.bohush.exercises.chapter04;

public class Exercise44 {

	public static void main(String[] args) {
		int oddregion = 0;
		for (int i = 0; i < 1000000; i++) {
			double x = Math.random() - 0.5;
			double y = Math.random() - 0.5;
			if (x < 0) {
				oddregion++;
			} else if ((x > 0) && (y > 0)) {
				double x1 = 0.5;
				double y1 = 0.0;
				double x2 = 0.0;
				double y2 = 0.5;
				double side = (x1 - x2) * (y - y2) - (x - x2) * (y1 - y2);
				if (side > 0) {
					oddregion++;
				}
				
			}
		}
		System.out.println(oddregion + " times");
	}

}
