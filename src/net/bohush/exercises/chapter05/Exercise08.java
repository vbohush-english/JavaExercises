package net.bohush.exercises.chapter05;

public class Exercise08 {

	public static void main(String[] args) {
		System.out.println("Celsius\t\tFahrenheit\t|\tFahrenheit\tCelsius");
		System.out.println("--------------------------------|---------------------------------");
		for (int celsius = 40, fahrenheit = 120; celsius > 30; celsius--, fahrenheit -= 10) {
			System.out.printf("%4.1f\t\t%7.1f\t\t|\t%7.1f\t\t%6.2f\n",
					(double)celsius, celsiusToFahrenheit(celsius), (double)fahrenheit, fahrenheitToCelsius(fahrenheit));	
		}
	}
	
	public static double celsiusToFahrenheit(double celsius) {
		return (9.0 / 5) * celsius + 32;
	}
	
	public static double fahrenheitToCelsius(double fahrenheit) {
		return (5.0 / 9) * (fahrenheit - 32);
	}

}
