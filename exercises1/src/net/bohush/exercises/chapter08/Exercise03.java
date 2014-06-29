package net.bohush.exercises.chapter08;

public class Exercise03 {

	public static void main(String[] args) {
		for (long i = 10000; i <= 100000000000L; i *= 10) {
			System.out.println(i + " " + new java.util.Date(i).toString());
		}
	}

}
