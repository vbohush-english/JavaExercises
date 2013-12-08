package ua.pp.viktor.chapter03;

import java.util.Scanner;

public class Exercise30 {

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
		int currentHour = (int)(totalHours % 24);
		int currentHour12;
		if (currentHour == 0) {
			currentHour12 = 12;
		} else if (currentHour > 12) {
			currentHour12 = currentHour - 12;
		} else {
			currentHour12 = currentHour;
		}
		System.out.println("Current time is " + currentHour12 + ":" + currentMinute + ":" + 
						currentSecond + " " + (currentHour > 11 ? "PM" : "AM"));
	}

}
