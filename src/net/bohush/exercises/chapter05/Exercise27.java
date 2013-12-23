package net.bohush.exercises.chapter05;

public class Exercise27 {

	public static void main(String[] args) {
		int count  = 0;
		int number = 1;
		while (count < 100) {
			if ((!Exercise03.isPalindrome(number)) &&
					Exercise10.isPrime(number) &&
					Exercise10.isPrime(Exercise03.reverse(number))) {
				System.out.print(number + (++count % 10 == 0 ? "\n" : "\t"));
			}
			number++;
		}		
	}

}
