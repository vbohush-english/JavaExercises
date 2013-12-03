package ua.pp.viktor.chapter01;

public class Exercise11 {

	public static void main(String[] args) {
		int secondsInYear = 365 * 24 * 60 * 60;
		int populationInYear = secondsInYear/7 - secondsInYear/13 + secondsInYear/45;
		System.out.println(312032486 + populationInYear);
		System.out.println(312032486 + 2*populationInYear);
		System.out.println(312032486 + 3*populationInYear);
		System.out.println(312032486 + 4*populationInYear);
		System.out.println(312032486 + 5*populationInYear);
		
	}

}
