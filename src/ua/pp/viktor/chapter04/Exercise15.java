package ua.pp.viktor.chapter04;

public class Exercise15 {
	public static void main(String[] args) {
		int count = 0;
		for (char i = '!'; i <= '~'; i++) {
			System.out.print(i + " ");
			count++;
			if (count % 10 == 0) {
				System.out.println();
			}
		}
	}
}
