package ua.pp.viktor.chapter02;

import java.util.Scanner;

public class Exercise04 {

	public static void main(String[] args) {
		System.out.print("Enter a number in pounds: ");
		Scanner input = new Scanner(System.in);
		double pounds = input.nextDouble();
		input.close();
		System.out.println(pounds + " pounds is " + (pounds * 0.454) + " kilograms");
	}

}
