package net.bohush.exercises.chapter04;

public class Exercise26 {

	public static void main(String[] args) {
		for (int count = 10000; count <= 100000; count += 10000) {
			double e = 1;
			double item = 1;
			for (int i = 2; i <= count; i ++) {
				e += item;
				item /= i;
			}
			System.out.println("e for i = " + count + ": " + e);
		}
	}

}
