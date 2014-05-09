package net.bohush.exercises.chapter35;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Exercise08 {

	public static void main(String[] args) {
		System.out.println("Celsius\t\tFahrenheit\t|\tFahrenheit\tCelsius");
		System.out.println("--------------------------------|---------------------------------");
		NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
		DecimalFormat decimalFormat = (DecimalFormat)numberFormat;
		decimalFormat.applyPattern("0.00");
		for (int celsius = 40, fahrenheit = 120; celsius > 30; celsius--, fahrenheit -= 10) {
			System.out.printf("%4s\t\t%7s\t\t|\t%7s\t\t%6s\n",
				decimalFormat.format(celsius), decimalFormat.format(celsiusToFahrenheit(celsius)), decimalFormat.format(fahrenheit), decimalFormat.format(fahrenheitToCelsius(fahrenheit)));	
		}
	}
	
	public static double celsiusToFahrenheit(double celsius) {
		return (9.0 / 5) * celsius + 32;
	}
	
	public static double fahrenheitToCelsius(double fahrenheit) {
		return (5.0 / 9) * (fahrenheit - 32);
	}

}
