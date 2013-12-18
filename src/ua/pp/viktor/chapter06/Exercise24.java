package ua.pp.viktor.chapter06;

public class Exercise24 {

	public static void main(String[] args) {		
		int[] deck = new int[52];
		String[] suits = { "Spades", "Hearts", "Diamonds", "Clubs" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"Jack", "Queen", "King" };

		// Initialize cards
		for (int i = 0; i < deck.length; i++)
			deck[i] = i;

		// Shuffle the cards
		for (int i = 0; i < deck.length; i++) {
			// Generate an index randomly
			int index = (int) (Math.random() * deck.length);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}

		boolean pickedAllCards = false;
		boolean[] cardSuit = new boolean[4];
		int count = 0;
		while (!pickedAllCards) {
			count++;
			
			int card = (int)(Math.random() * deck.length);
			String suit = suits[deck[card] / 13];
			String rank = ranks[deck[card] % 13];
			
			if (!cardSuit[deck[card] / 13]) {
				cardSuit[deck[card] / 13] = true;
				System.out.println("Card number " + deck[card] + ": " + rank + " of " + suit);
			}
			pickedAllCards = cardSuit[0] && cardSuit[1] && cardSuit[2] && cardSuit[3];
		}
		System.out.println("Number of picks: " + count);
	}

	

}
