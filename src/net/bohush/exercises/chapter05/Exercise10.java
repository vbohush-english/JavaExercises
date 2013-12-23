package net.bohush.exercises.chapter05;

public class Exercise10 {

	public static void main(String[] args) {
		for (int i = 1, count = 0; i < 10000; i++) {
			if (isPrime(i)) {
				System.out.print(i + (++count % 10 == 0 ? "\n" : "\t"));
			}
		}
	}

	public static boolean isPrime(int number) {
		for (int divisor = 2; divisor <= number / 2; divisor++) {
			if (number % divisor == 0) {
				return false;
			}
		}
		return true;
	}
}
