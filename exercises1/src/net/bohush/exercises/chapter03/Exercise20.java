package net.bohush.exercises.chapter03;

import java.util.Scanner;

public class Exercise20 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the temperature in Fahrenheit: ");
		double temperature = input.nextDouble();
		if ((temperature < -58) || (temperature > 41)) {
			input.close();
			System.out.print("The temperature is not valid, it must be between -58F and 41F");
			System.exit(1);
		}
		System.out.print("Enter the wind speed in miles per hour: ");
		double speed = input.nextDouble();
		input.close();
		if (speed < 2) {
			System.out.print("The speed is not valid, it must be greater than or equal to 2");
			System.exit(1);
		}
		double index = 35.74 + 0.6215 * temperature - 35.75 * Math.pow(speed, 0.16) 
				+ 0.4275 * temperature * Math.pow(speed, 0.16);
		System.out.println("The wind chill index is " + index);
	}

}
