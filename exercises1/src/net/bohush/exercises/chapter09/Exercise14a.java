package net.bohush.exercises.chapter09;

public class Exercise14a {

	public static void main(String[] args) {
		int sum = 0;
		for (int i = 0; i < args.length; i++) {
			sum += Integer.parseInt(args[i]);
		}
		System.out.println("Total is " + sum);
	}

}
