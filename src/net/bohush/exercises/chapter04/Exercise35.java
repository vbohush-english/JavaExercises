package net.bohush.exercises.chapter04;

public class Exercise35 {

	public static void main(String[] args) {
		double sum = 1 / (1 + Math.sqrt(2));
		for (int i = 2; i <=624; i++) {
			sum += 1 / (Math.sqrt(i) + Math.sqrt(i + 1));
		}
		System.out.println(sum);
	}

}
