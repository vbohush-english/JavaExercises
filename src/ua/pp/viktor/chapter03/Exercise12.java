package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise12 {

	public static void main(String[] args) {
		System.out.print("Enter an integer ");
		Scanner input = new Scanner(System.in);
		int number = input.nextInt();
		input.close();
		if ((number % 5 == 0) && (number % 6 == 0)) {
			System.out.println(number + " is divisible by both 5 and 6");
		} else if ((number % 5 != 0) && (number % 6 != 0)) {
			System.out.println(number + " is not divisible by either 5 or 6");
		} else {
			System.out.println(number + " is divisible by 5 or 6, but not both");
		}
	}

}
