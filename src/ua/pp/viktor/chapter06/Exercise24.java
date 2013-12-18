package ua.pp.viktor.chapter06;

public class Exercise24 {

	public static void main(String[] args) {
		boolean pickedAllCards = false;
		boolean[] cardSuit = new boolean[4];
		int count = 0;
		while (!pickedAllCards) {
			count++;
			int card = getCard();
			if (!cardSuit[getSuitOfCard(card)]) {
				cardSuit[getSuitOfCard(card)] = true;
				System.out.println(getStringOfCard(card));
			}
			pickedAllCards = cardSuit[0] && cardSuit[1] && cardSuit[2] && cardSuit[3];
		}
		System.out.println("Number of picks: " + count);
	}
	
	public static int getCard() {
		int rank = 1 + (int)(Math.random() * 13);
		int suit = (int)(Math.random() * 4);
		return rank*100 + suit;
	}
	
	public static int getSuitOfCard(int card) {
		return card % 100;
	}
	
	public static String getStringOfCard(int card) {
		int rank = card / 100;
		int suit = card % 100;
		String result = "";
		switch (rank) {
		case 1: result += "Ace"; break;
		case 11: result += "Jack"; break;
		case 12: result += "Queen"; break;
		case 13: result += "King"; break;
		default: result += rank; break;
		}
		result += " of ";
		switch (suit) {
		case 0: result += "Clubs"; break;
		case 1: result += "Diamonds"; break;
		case 2: result += "Hearts"; break;
		case 3: result += "Spades"; break;
		}
		return result;
	}
	
	

}
