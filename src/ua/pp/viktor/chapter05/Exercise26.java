package ua.pp.viktor.chapter05;

public class Exercise26 {

	public static void main(String[] args) {
		int count  = 0;
		int number = 1;
		while (count < 100) {
			if (Exercise03.isPalindrome(number) && Exercise10.isPrime(number)) {
				System.out.print(number + (++count % 10 == 0 ? "\n" : "\t"));
			}
			number++;
		}		
	}
	
}
