package net.bohush.exercises.chapter24;

public class Exercise14 {

	public static void main(String[] args) {
		int begin = 8_000_000;
		int end = 18_000_000;
		int step = 2_000_000;
		
		System.out.print("\t\t|");
		for (int n = begin; n <= end; n += step) {
			System.out.printf("%15d", n);
		}
		System.out.println();
		System.out.println("----------------|-------------------------------------------------------------------------------------------");
		System.out.print("Listing 24.4\t|");
		for (int n = begin; n <= end; n += step) {
			long startTime = System.currentTimeMillis();
			getPrime1(n);
			long endTime = System.currentTimeMillis();
			long executionTime = (endTime - startTime) / 1000;
			System.out.printf("%15d", executionTime);
		}
		System.out.println();
		System.out.println("----------------|-------------------------------------------------------------------------------------------");
		System.out.print("Listing 24.5\t|");
		for (int n = begin; n <= end; n += step) {
			long startTime = System.currentTimeMillis();
			getPrime2(n);
			long endTime = System.currentTimeMillis();
			long executionTime = (endTime - startTime) / 1000;
			System.out.printf("%15d", executionTime);
		}
		System.out.println();
		System.out.println("----------------|-------------------------------------------------------------------------------------------");
		System.out.print("Listing 24.6\t|");
		for (int n = begin; n <= end; n += step) {
			long startTime = System.currentTimeMillis();
			getPrime3(n);
			long endTime = System.currentTimeMillis();
			long executionTime = (endTime - startTime) / 1000;
			System.out.printf("%15d", executionTime);
		}

	}

	
	public static int getPrime1(int n) {
		int count = 0;
		int number = 2;
		while (number <= n) {
			boolean isPrime = true;
			for (int divisor = 2; divisor <= (int) (Math.sqrt(number)); divisor++) {
				if (number % divisor == 0) {
					isPrime = false; 
					break;
				}
			}
			if (isPrime) {
				count++;
			}
			number++;
		}
		return count;
	}
	
	public static int getPrime2(int n) {
		java.util.List<Integer> list = new java.util.ArrayList<Integer>();
		int count = 0;
		int number = 2;
		int squareRoot = 1;
		while (number <= n) {
			boolean isPrime = true;
			if (squareRoot * squareRoot < number)
				squareRoot++;
			for (int k = 0; k < list.size() && list.get(k) <= squareRoot; k++) {
				if (number % list.get(k) == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				count++; 
				list.add(number);
			}
			number++;
		}
		return count;
	}
	
	public static int getPrime3(int n) {
		boolean[] primes = new boolean[n + 1];
		for (int i = 0; i < primes.length; i++) {
			primes[i] = true;
		}
		for (int k = 2; k <= n / k; k++) {
			if (primes[k]) {
				for (int i = k; i <= n / k; i++) {
					primes[k * i] = false;
				}
			}
		}
		int count = 0;
		for (int i = 2; i < primes.length; i++) {
			if (primes[i]) {
				count++;
			}
		}
		return count;
	}
}
