package net.bohush.exercises.chapter04;

public class Exercise20 {

	public static void main(String[] args) {
		final int NUMBER_OF_PRIMES_PER_LINE = 8;
		int count = 0;
		int number = 2;

		System.out.println("The prime numbers from 2 to 1000 are \n");

		while (number < 1000) {
			boolean isPrime = true;

			for (int divisor = 2; divisor <= number / 2; divisor++) {
				if (number % divisor == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				count++;
				if (count % NUMBER_OF_PRIMES_PER_LINE == 0) {
					System.out.println(number);
				} else
					System.out.print(number + " ");
			}

			number++;
		}
	}

}
