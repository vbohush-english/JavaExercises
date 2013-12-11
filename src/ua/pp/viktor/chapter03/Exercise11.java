package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise11 {

	public static void main(String[] args) {
		System.out.print("Enter month: ");
		Scanner input = new Scanner(System.in);
		int month = input.nextInt();
		System.out.print("Enter year: ");
		int year = input.nextInt();
		input.close();
		int days = 0;
		switch(month) {
		case 1: days = 31; System.out.print("January"); break;
		case 2: days = 28; System.out.print("February"); break;
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
		if (((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))&&(month == 2)) {
			days++;
		}
		System.out.println(" " + year + " had " + days + " days");
	}

}
