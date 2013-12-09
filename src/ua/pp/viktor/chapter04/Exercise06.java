package ua.pp.viktor.chapter04;

public class Exercise06 {

	public static void main(String[] args) {
		System.out.println("Miles\tKilometers\t|\tKilometers\tMiles");
		for (int miles = 1, kilometers = 20; miles <= 10; miles++, kilometers += 5) {
			System.out.printf("%d\t%-6.3f\t\t|\t%d\t\t%-6.3f\n", miles, miles * 1.609, kilometers, kilometers * 0.621);
		}
	}

}
