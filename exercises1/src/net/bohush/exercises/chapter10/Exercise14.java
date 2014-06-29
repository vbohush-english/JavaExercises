package net.bohush.exercises.chapter10;

public class Exercise14 {

	public static void main(String[] args) {
		MyDate m = new MyDate(34355555133101L);
		System.out.println(m.getDay() + "." + m.getMonth() + "." + m.getYear());
	}

}


class MyDate {
	private int year;
	private int month;
	private int day;
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	
	public MyDate(long elapsedTime) {
		setDate(elapsedTime);
	}
	
	public MyDate() {
		this(System.currentTimeMillis());
	}
	
	public MyDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	
	public void setDate(long elapsedTime) {
		long totalSeconds = elapsedTime / 1000;
		long totalMinutes = totalSeconds / 60;
		long totalHours = totalMinutes / 60;
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
		this.year = year;
		this.month = month - 1;
		this.day = (int)(totalDays);
	}
	
	private static int getNumberOfDaysInMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12)
			return 31;

		if (month == 4 || month == 6 || month == 9 || month == 11)
			return 30;

		if (month == 2)
			return isLeapYear(year) ? 29 : 28;

		return 0;
	}

	private static boolean isLeapYear(int year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}
	
	private static int daysInYear(int year) {
		return isLeapYear(year) ? 366: 365;
	}
}