package net.bohush.exercises.chapter15;

import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Exercise06 {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter full year (e.g., 2001): ");
		int year = input.nextInt();
		System.out.print("Enter month in number between 1 and 12: ");
		int month = input.nextInt();
		printMonth(new GregorianCalendar(year, month - 1, 1));
	}

	public static void printMonth(Calendar date) {
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		System.out.println("         " + months[date.get(Calendar.MONTH)] + " " + date.get(Calendar.YEAR));
		System.out.println("-----------------------------");
		System.out.println(" Mon Tue Wed Thu Fri Sat Sun");
		date.set(Calendar.DAY_OF_MONTH, 1);
		int startDay = date.get(Calendar.DAY_OF_WEEK) - 2;	
		if (startDay == -1) {
			startDay = 6;
		}
		int endDay = date.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < endDay + startDay; i++) {
			if (i >= startDay) {
				System.out.printf("%4d",  (i - startDay + 1));
				if ((i + 1) % 7 == 0) {
					System.out.println();
				}
			} else {
				System.out.print("    ");				
			}
		}
	}

}