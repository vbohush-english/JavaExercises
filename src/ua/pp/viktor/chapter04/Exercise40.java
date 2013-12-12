package ua.pp.viktor.chapter04;

public class Exercise40 {

	public static void main(String[] args) {
		int heads = 0;
		int tails = 0;
		for (int i = 0; i < 1000000; i++) {
			int coin = (int)(Math.random() * 2);
			if (coin == 0) {
				heads++;
			} else {
				tails++;
			}
		}
		System.out.println("Heads - " + heads);
		System.out.println("Tails - " + tails);
	}

}
