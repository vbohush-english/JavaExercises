package net.bohush.exercises.chapter05;

public class Exercise24 {

	public static void main(String[] args) {
		long totalMilliseconds = System.currentTimeMillis();
		
		long totalSeconds = totalMilliseconds / 1000;

		long currentSecond = totalSeconds % 60;

		long totalMinutes = totalSeconds / 60;

		long currentMinute = totalMinutes % 60;

		long totalHours = totalMinutes / 60;

		long currentHour = totalHours % 24;
		
		long totalDays = totalHours / 24;
		totalDays++;
		
		int year = 1970;
		while (totalDays > daysInYear(year)) {
			totalDays -= daysInYear(year++);
		}
		
		int month = 1;
		while (totalDays > getNumberOfDaysInMonth(year, month)) {
			totalDays -= getNumberOfDaysInMonth(year, month++);
		}
			
			
		System.out.print("Current time is " + currentHour + ":" + currentMinute + ":" + currentSecond + " GMT ");
		System.out.print(totalDays + "." + month + "." + year);
		
		
	}


	/** Get the number of days in a month */
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
	
	public static int daysInYear(int year) {
		return isLeapYear(year) ? 366: 365;
	}
}
