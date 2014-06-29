package net.bohush.exercises.chapter08;

import javax.swing.JOptionPane;

public class Exercise06 {
	public static void main(String[] args) {

		String input = JOptionPane.showInputDialog("Enter full year (e.g., 2001): ");
		int year = Integer.parseInt(input);
		
		input = JOptionPane.showInputDialog("Enter month in number between 1 and 12: ");
		int month = Integer.parseInt(input);

		JOptionPane.showMessageDialog(null, getMonth(year, month));
	}

	/** Print the calendar for a month in a year */
	public static String getMonth(int year, int month) {
		return getMonthTitle(year, month) + getMonthBody(year, month);
	}

	/** Print the month title, e.g., May, 1999 */
	public static String getMonthTitle(int year, int month) {
		String result = "";
		result += "         " + getMonthName(month) + " " + year + "\n";
		result += "-----------------------------" + "\n";
		result += " Sun Mon Tue Wed Thu Fri Sat" + "\n";
		return result;
	}



	/** Print month body */
	public static String getMonthBody(int year, int month) {
		String result = "";
		int startDay = getStartDay(year, month);

		int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);

		int i = 0;
		for (i = 0; i < startDay; i++)
			result += "    ";

		for (i = 1; i <= numberOfDaysInMonth; i++) {
			if (i > 9) {
				result += "  " + i;
			}else {
				result += "   " + i;
			}

			if ((i + startDay) % 7 == 0)
				result += "\n";
		}

		result += "\n";
		return result;
	}

	public static int getStartDay(int year, int month) {
		final int START_DAY_FOR_JAN_1_1800 = 3;
		int totalNumberOfDays = getTotalNumberOfDays(year, month);
		return (totalNumberOfDays + START_DAY_FOR_JAN_1_1800) % 7;
	}
	
	public static int getTotalNumberOfDays(int year, int month) {
		int total = 0;

		for (int i = 1800; i < year; i++)
			if (isLeapYear(i))
				total = total + 366;
			else
				total = total + 365;
		for (int i = 1; i < month; i++)
			total = total + getNumberOfDaysInMonth(year, i);

		return total;
	}
	
	public static String getMonthName(int monthNumber) {
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		return months[monthNumber - 1];
	}
	
	public static int getNumberOfDaysInMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12)
			return 31;

		if (month == 4 || month == 6 || month == 9 || month == 11)
			return 30;

		if (month == 2)
			return isLeapYear(year) ? 29 : 28;

		return 0;
	}

	public static boolean isLeapYear(int year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}
}
