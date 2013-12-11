package ua.pp.viktor.chapter04;

import java.util.Scanner;

public class Exercise28 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter year: (e.g., 2013): ");
		int year = input.nextInt();
		System.out.print("Enter day: 1-7: ");
		int day = input.nextInt();
		
		input.close();
		
		boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
		
		for (int month = 1; month <=12; month++) {
			int days = 0;
			switch(month) {
			case 1: days = 0; System.out.print("January"); break;
			case 2: days = 31; System.out.print("February"); break;
			case 3: days = isLeapYear ? 29 : 28; System.out.print("March"); break;
			case 4: days = 31; System.out.print("April"); break;
			case 5: days = 30; System.out.print("May"); break;
			case 6: days = 31; System.out.print("June"); break;
			case 7: days = 30; System.out.print("July"); break;
			case 8: days = 31; System.out.print("August"); break;
			case 9: days = 31; System.out.print("September"); break;
			case 10: days = 30; System.out.print("October"); break;
			case 11: days = 31; System.out.print("November"); break;
			case 12: days = 30; System.out.print("December"); break;
			}	
			
			day = (day + days) % 7;
			System.out.print(" 1, " + year + " is ");
			
			switch(day) {
			case 1: System.out.println("Monday"); break;
			case 2: System.out.println("Tuesday"); break;
			case 3: System.out.println("Wednesday"); break;
			case 4: System.out.println("Thursday"); break;
			case 5: System.out.println("Friday"); break;
			case 6: System.out.println("Saturday"); break;
			case 0: System.out.println("Sunday"); break;			
			}
		}
	}

}
