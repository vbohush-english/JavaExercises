package ua.pp.viktor.chapter05;

public class Exercise16 {

	public static void main(String[] args) {
		for (int year = 2000; year <= 2020; year++) {
			System.out.println(year + " - " + numberOfDaysInAYear(year));
		}
	}
	
	public static int numberOfDaysInAYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? 366 : 365;
	}

}
