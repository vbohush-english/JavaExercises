package net.bohush.exercises.chapter05;

public class Exercise12 {

	public static void main(String[] args) {
		printChars('1', 'Z', 10);
	}
	
	public static void printChars(char ch1, char ch2, int numberPerLine) {
		for (char ch = ch1; ch <= ch2; ch++) {
			System.out.print(ch + ((ch - ch1 + 1) % numberPerLine == 0 ? "\n" : "\t"));
		}
	}

}
