package net.bohush.exercises.chapter06;

public class Exercise06 {

	public static void main(String[] args) {
		final int NUMBER_OF_PRIMES = 50;
		final int NUMBER_OF_PRIMES_PER_LINE = 10;
		int count = 0;
		int number = 2;
		
		int[] numbers = new int[NUMBER_OF_PRIMES];

		System.out.println("The first 50 prime numbers are \n");

		while (count < NUMBER_OF_PRIMES) {
			boolean isPrime = true;

			for (int i = 0; i < count && numbers[i] <= Math.sqrt(number); i++) {
				if (number % numbers[i] == 0) {
					isPrime = false;
					break;
				}
			}  
		      
			if (isPrime) {
				numbers[count] = number;
				System.out.print(number + (++count % NUMBER_OF_PRIMES_PER_LINE == 0 ? "\n" : " "));
			}
			number++;
		}
	}

}
