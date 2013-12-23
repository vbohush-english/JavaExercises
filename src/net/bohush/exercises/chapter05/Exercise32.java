package net.bohush.exercises.chapter05;

public class Exercise32 {

	public static void main(String[] args) {
		int winTimes = 0;
		for (int i = 0; i < 10000; i++ ) {
			if (didYouWin()) {
				winTimes++;
			}
		}
		System.out.println(winTimes);
	}
	
	public static boolean didYouWin() {
		boolean result;
		int dice1 = (int)(Math.random() * 6) + 1;
		int dice2 = (int)(Math.random() * 6) + 1;
		int sum = dice1 + dice2;
		if ((sum == 2) || (sum == 3) || (sum == 12)) {
			result = false;
		} else if ((sum == 7) || (sum == 11)) {
			result = true;
		} else {
			int lastSum = sum;
			do {
				dice1 = (int)(Math.random() * 6) + 1;
				dice2 = (int)(Math.random() * 6) + 1;
				sum = dice1 + dice2;
			} while ((sum != lastSum) && (sum != 7));
			if (sum == lastSum) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

}
