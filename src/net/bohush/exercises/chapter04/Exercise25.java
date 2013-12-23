package net.bohush.exercises.chapter04;

public class Exercise25 {

	public static void main(String[] args) {
		for (int count = 10000; count <= 100000; count += 10000) {
			double pi = 0;
			for (int i = 1; i <= count; i ++) {
				pi +=  Math.pow(-1, i + 1)/ (2 * i - 1);
			}
			System.out.println("P for i = " + count + ": " + 4 * pi);
		}
	}

}
