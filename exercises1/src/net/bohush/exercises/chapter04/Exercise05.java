package net.bohush.exercises.chapter04;

public class Exercise05 {

	public static void main(String[] args) {
		System.out.println("Kilograms\tPounds\t|\tPounds\t\tKilograms");
		for (int kilograms = 1, pounds = 20; kilograms < 200; kilograms += 2, pounds += 5) {
			System.out.printf("%d\t\t%6.1f\t|\t%d\t\t%6.2f\n", kilograms, kilograms * 2.2, pounds, pounds * 0.453);
		}
	}

}
