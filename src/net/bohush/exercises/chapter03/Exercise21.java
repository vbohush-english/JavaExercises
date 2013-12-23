package net.bohush.exercises.chapter03;

import java.util.Scanner;

public class Exercise21 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter year: (e.g., 2012): ");
		int year = input.nextInt();
		System.out.print("Enter month: 1-12: ");
		int month = input.nextInt();
		System.out.print("Enter the day of the month: 1-31: ");
		int dayOfMonth = input.nextInt();
		input.close();
		
		if ((month == 1)||(month == 2)) {
			month += 12;
			year--;
		}
		int yearOfCentury = year % 100;
		int century = year / 100;		
		int day = (dayOfMonth + (26 * (month + 1)) / 10 + yearOfCentury + yearOfCentury / 4 + century / 4 + 5 * century) % 7;
		
		System.out.print("Day of the week is ");
		switch(day) {
		case 0: System.out.print("Saturday"); break;
		case 1: System.out.print("Sunday"); break;
		case 2: System.out.print("Monday"); break;
		case 3: System.out.print("Tuesday"); break;
		case 4: System.out.print("Wednesday"); break;
		case 5: System.out.print("Thursday"); break;
		case 6: System.out.print("Friday"); break;
		}
	}

}
