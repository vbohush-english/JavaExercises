package net.bohush.exercises.chapter20;

public class Exercise06 {

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println(i + " - " + sum(i));	
		}		
	}

	private static double sum(int number) {
		if (number == 1) {
			return 0.5;
		} else {
			return sum(number - 1) + number / (number + 1.0);
		}
	}

}
