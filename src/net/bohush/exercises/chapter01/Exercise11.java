package net.bohush.exercises.chapter01;

public class Exercise11 {

	public static void main(String[] args) {
		int secondsInYear = 365 * 24 * 60 * 60;
		double populationInYear = secondsInYear/7.0 - secondsInYear/13.0 + secondsInYear/45.0;
		System.out.println(312032486 + (int)(populationInYear));
		System.out.println(312032486 + (int)(2*populationInYear));
		System.out.println(312032486 + (int)(3*populationInYear));
		System.out.println(312032486 + (int)(4*populationInYear));
		System.out.println(312032486 + (int)(5*populationInYear));
		
	}

}
