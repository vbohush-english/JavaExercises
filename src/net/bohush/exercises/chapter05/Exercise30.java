package net.bohush.exercises.chapter05;

public class Exercise30 {

	public static void main(String[] args) {
		int dice1 = (int)(Math.random() * 6) + 1;
		int dice2 = (int)(Math.random() * 6) + 1;
		int sum = dice1 + dice2;
		System.out.println("You rolled " + dice1 + " + " + dice2 + " = " + sum);
		if ((sum == 2) || (sum == 3) || (sum == 12)) {
			System.out.println("You lose");
		} else if ((sum == 7) || (sum == 11)) {
			System.out.println("You win");
		} else {
			System.out.println("point is " + sum);
			int lastSum = sum;
			do {
				dice1 = (int)(Math.random() * 6) + 1;
				dice2 = (int)(Math.random() * 6) + 1;
				sum = dice1 + dice2;
				System.out.println("You rolled " + dice1 + " + " + dice2 + " = " + sum);
			} while ((sum != lastSum) && (sum != 7));
			if (sum == lastSum) {
				System.out.println("You win");	
			} else {
				System.out.println("You lose");
			}
		}
	}

}
