package net.bohush.exercises.chapter10;

public class Exercise06 {

	public static void main(String[] args) {
		StackOfIntegers stack = new StackOfIntegers();
		for (int i = 2; i <= 120; i++) {
			if (isPrime(i)) {
				stack.push(i);
			}
		}
		while(!stack.empty()) {
			System.out.print(stack.pop() + " ");
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
