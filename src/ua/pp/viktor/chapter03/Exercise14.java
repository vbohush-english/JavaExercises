package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise14 {

	public static void main(String[] args) {
		int coin = (int)(Math.random() * 2);
		System.out.print("Enter 0 for heads or 1 for tail: ");
		Scanner input = new Scanner(System.in);
		int userCoin = input.nextInt();
		input.close();
		if (coin == userCoin) {
			System.out.println("You guessed it");
		} else {
			System.out.println("You guessed wrong");			
		}
	}

}
