package net.bohush.exercises.chapter04;

import java.util.Scanner;

public class Exercise29 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter year: (e.g., 2013): ");
		int year = input.nextInt();
		System.out.print("Enter day: 1-7: ");
		int firstDay = input.nextInt();
		
		input.close();
		
		boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
		
		for (int month = 1; month <=12; month++) {
			
			System.out.print("\n\n\t\t");
			
			int days = 0;
			
			switch(month) {
			case 1: days = 31; System.out.print("January"); break;
			case 2: days = isLeapYear ? 29 : 28; System.out.print("February"); break;
			case 3: days = 31; System.out.print("March"); break;
			case 4: days = 30; System.out.print("April"); break;
			case 5: days = 31; System.out.print("May"); break;
			case 6: days = 30; System.out.print("June"); break;
			case 7: days = 31; System.out.print("July"); break;
			case 8: days = 31; System.out.print("August"); break;
			case 9: days = 30; System.out.print("September"); break;
			case 10: days = 31; System.out.print("October"); break;
			case 11: days = 30; System.out.print("November"); break;
			case 12: days = 31; System.out.print("December"); break;
			}	
			
			System.out.println(" " + year + "\n---------------------------------------------------");
			System.out.println("Sun\tMon\tTue\tWed\tThu\tFri\tSat");
			
			int count = 0;
			for (int i = 1; i < days + firstDay; i++) {
				if (i >= firstDay) {
					System.out.print(i - firstDay + 1);
				}
				System.out.print("\t");
				if (++count % 7 == 0) {
					System.out.print("\n");	
				}
			}
			firstDay = count % 7 + 1;
		}
	}

}
