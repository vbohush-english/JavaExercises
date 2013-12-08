package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise32 {

	public static void main(String[] args) {
		System.out.print("Enter three points for p0, p1, and p2: ");
		Scanner input = new Scanner(System.in);
		double x0 = input.nextDouble();
		double y0 = input.nextDouble();
		double x1 = input.nextDouble();
		double y1 = input.nextDouble();
		double x2 = input.nextDouble();
		double y2 = input.nextDouble();
		input.close();
		double position = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
		System.out.print("p2 is on the ");
		if (position > 0) {
			System.out.print("left side of the");
		} else if (position < 0) {
			System.out.print("right side of the");
		} else {
			System.out.print("same");
		}
		System.out.println(" line");
	}

}
