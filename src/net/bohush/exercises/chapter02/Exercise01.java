package net.bohush.exercises.chapter02;

import java.util.Scanner;

public class Exercise01 {

	public static void main(String[] args) {
		System.out.print("Enter a degree in Celsius: ");
		Scanner input = new Scanner(System.in);
		double celsius = input.nextDouble();
		double fahrenheit = (9.0 / 5.0) * celsius + 32;
		System.out.println(celsius + " Celsius is " + fahrenheit + " Fahrenheit");
		input.close();
	}

}
