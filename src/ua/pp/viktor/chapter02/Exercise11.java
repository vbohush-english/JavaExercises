package ua.pp.viktor.chapter02;

import java.util.Scanner;

public class Exercise11 {

	public static void main(String[] args) {
		int population = 312032486;
		int secondsInYear = 365 * 24 * 60 * 60;
		double populationInYear = secondsInYear/7.0 - secondsInYear/13.0 + secondsInYear/45.0;
		System.out.print("Enter the number of years: ");
		Scanner input = new Scanner(System.in);
		int numberOfYears = input.nextInt();
		input.close();
		System.out.println("The population in " + numberOfYears + " years is " + (population + (int)(numberOfYears*populationInYear)));
	}

}
