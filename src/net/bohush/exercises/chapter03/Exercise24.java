package net.bohush.exercises.chapter03;

public class Exercise24 {

	public static void main(String[] args) {
		int rank = 1 + (int)(Math.random() * 13);
		int suit = (int)(Math.random() * 4);
		System.out.print("The card you picked is ");
		switch (rank) {
		case 1: System.out.print("Ace"); break;
		case 11: System.out.print("Jack"); break;
		case 12: System.out.print("Queen"); break;
		case 13: System.out.print("King"); break;
		default: System.out.print(rank); break;
		}
		System.out.print(" of ");
		switch (suit) {
		case 0: System.out.print("Clubs"); break;
		case 1: System.out.print("Diamonds"); break;
		case 2: System.out.print("Hearts"); break;
		case 3: System.out.print("Spades"); break;
		}
	}

}
