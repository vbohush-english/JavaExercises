package net.bohush.exercises.chapter04;

public class Exercise03 {

	public static void main(String[] args) {
		System.out.println("Kilograms\tPounds");
		for (int kilograms = 1; kilograms < 200; kilograms += 2) {
			System.out.println(kilograms + "\t\ts" + (int)(kilograms * 2.2 * 10) / 10.0);	
		}
	}

}
