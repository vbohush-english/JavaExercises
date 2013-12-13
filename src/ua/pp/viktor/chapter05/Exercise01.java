package ua.pp.viktor.chapter05;

public class Exercise01 {

	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			System.out.print(getPentagonalNumber(i) + (i % 10 == 0 ? "\n" : "\t"));
		}
	}
	
	public static int getPentagonalNumber(int n) {
		return n * (3 * n - 1) / 2;
	}

}
