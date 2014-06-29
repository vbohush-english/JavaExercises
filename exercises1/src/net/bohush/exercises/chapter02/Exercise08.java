package net.bohush.exercises.chapter02;

import java.util.Scanner;

public class Exercise08 {
	public static void main(String[] args) {
		System.out.print("Enter the time zone offset to GMT: ");
		Scanner input = new Scanner(System.in);
		int offset = input.nextInt();
		input.close();
		long totalMilliseconds = System.currentTimeMillis();
		totalMilliseconds += offset * 60 * 60 * 1000;
		long totalSeconds = totalMilliseconds / 1000;
		long currentSecond = totalSeconds % 60;
		long totalMinutes = totalSeconds / 60;
		long currentMinute = totalMinutes % 60;
		long totalHours = totalMinutes / 60;
		long currentHour = totalHours % 24;
		System.out.println("Current time is " + currentHour + ":" + currentMinute + ":" + currentSecond + " GMT");
	}

}
