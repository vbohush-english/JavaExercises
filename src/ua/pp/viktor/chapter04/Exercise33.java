package ua.pp.viktor.chapter04;

public class Exercise33 {

	public static void main(String[] args) {
		System.out.print("Perfect numbers: ");
		for (int number = 1; number < 10000; number++) {
			int sum = 0;
			for (int i = 1; i < number; i++) {
				if (number % i == 0) {
					sum += i;
				}
			}
			if (number == sum)
				System.out.print(number + " ");
		}
	}

}
