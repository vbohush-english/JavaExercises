package net.bohush.exercises.chapter03;

import java.util.Scanner;

public class Exercise05 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter today's day: ");
		int todaysDay = input.nextInt();
		System.out.print("Enter the number of days elapsed since today: ");
		int elepsedDays = input.nextInt();
		input.close();
		
		elepsedDays = (todaysDay + elepsedDays) % 7;
		
		System.out.print("Today is ");
		switch(todaysDay) {
		case 0: System.out.print("Sunday"); break;
		case 1: System.out.print("Monday"); break;
		case 2: System.out.print("Tuesday"); break;
		case 3: System.out.print("Wednesday"); break;
		case 4: System.out.print("Thursday"); break;
		case 5: System.out.print("Friday"); break;
		case 6: System.out.print("Saturday"); break;
		}
		System.out.print(" and the future day is ");
		switch(elepsedDays) {
		case (0): System.out.print("Sunday"); break;
		case (1): System.out.print("Monday"); break;
		case (2): System.out.print("Tuesday"); break;
		case (3): System.out.print("Wednesday"); break;
		case (4): System.out.print("Thursday"); break;
		case (5): System.out.print("Friday"); break;
		case (6): System.out.print("Saturday"); break;
		}
	}
}
