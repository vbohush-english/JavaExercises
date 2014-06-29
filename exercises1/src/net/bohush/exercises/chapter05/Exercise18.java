package net.bohush.exercises.chapter05;

public class Exercise18 {

	public static void main(String[] args) {
		System.out.println("Number\tSquareRoot");
		System.out.println("------------------");
		for (int number = 0; number <= 20; number += 2) {
			System.out.printf("%d\t%9.4f\n", number, Math.sqrt(number));	
		}
	}

}
