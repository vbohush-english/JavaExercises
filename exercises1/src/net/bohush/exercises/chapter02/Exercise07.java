package net.bohush.exercises.chapter02;

import java.util.Scanner;

public class Exercise07 {

	public static void main(String[] args) {
		System.out.print("Enter the number of minutes: ");
		Scanner input = new Scanner(System.in);
		int minutes = input.nextInt();
		input.close();
		int minutesInDay = 60 * 24;
		int minutesInYear = minutesInDay * 365;
		int years =  minutes / minutesInYear;
		int days = (minutes % minutesInYear) / minutesInDay;
		System.out.println(minutes + " minutes is approximately " + years + " years and " + days);
	}

}
