package net.bohush.exercises.chapter05;

public class Exercise38 {

	public static void main(String[] args) {
		print100Char('A', 'Z');
		print100Char('0', '9');
	}

	public static void print100Char(char ch1, char ch2) {
		for (int i = 1; i <= 100; i++) {
			System.out.print(getRandomCharacter(ch1, ch2));
			if (i % 10 == 0) {
				System.out.println();
			}
		}
	}
	
	public static char getRandomCharacter(char ch1, char ch2) {
		return (char)(ch1 + Math.random() * (ch2 - ch1 + 1));
	}
}
