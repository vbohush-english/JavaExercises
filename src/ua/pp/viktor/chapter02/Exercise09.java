package ua.pp.viktor.chapter02;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		System.out.print("Enter v0, v1, and t: ");
		Scanner input = new Scanner(System.in);
		double v0 = input.nextDouble();
		double v1 = input.nextDouble();
		double t = input.nextDouble();
		input.close();
		double a = (v1 - v0) / t;
		System.out.println("The average acceleration is " + a);
	}

}
