package ua.pp.viktor.chapter05;

public class Exercise29 {

	public static void main(String[] args) {
		for (int number = 3; number <= 1000; number++) {
			if (Exercise10.isPrime(number) && Exercise10.isPrime(number + 2)) {
				System.out.println("(" + number + ", " + (number + 2) + ")");
			}
		}
	}

}
