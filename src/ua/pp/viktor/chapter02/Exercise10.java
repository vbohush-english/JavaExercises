package ua.pp.viktor.chapter02;

import java.util.Scanner;

public class Exercise10 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the amount of water in kilograms: ");
		double M = input.nextDouble();
		System.out.print("Enter the initial temperature: ");
		double initialTemperature = input.nextDouble();
		System.out.print("Enter the final temperature: ");
		double finalTemperature = input.nextDouble();
		input.close();
		double Q = M * (finalTemperature - initialTemperature) * 4184;
		System.out.println("The energy needed is  " + Q);
	}

}
