package net.bohush.exercises.chapter05;

public class Exercise13 {

	public static void main(String[] args) {
		System.out.println("i\t   m(i)");
		System.out.println("---------------");
		for (int i = 1; i <= 20; i++) {
			System.out.printf("%d\t%7.4f\n", i, m(i));	
		}
	}
	public static double m(double i) {
		double result = 0;
		for (int j = 1; j <= i; j++) {
			result += j / (j + 1.0);
		}
		return result;
	}

}
