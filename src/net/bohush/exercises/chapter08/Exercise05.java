package net.bohush.exercises.chapter08;

import java.util.GregorianCalendar;

public class Exercise05 {

	public static void main(String[] args) {
		GregorianCalendar calendar = new GregorianCalendar();
		printCalendar(calendar);
		calendar.setTimeInMillis(1234567898765L);
		printCalendar(calendar);
	}
	
	public static void printCalendar(GregorianCalendar calendar) {
		System.out.println(calendar.get(GregorianCalendar.DAY_OF_MONTH) + "." +
				calendar.get(GregorianCalendar.MONTH) + "." + 
				calendar.get(GregorianCalendar.YEAR));	
	}
	
}
