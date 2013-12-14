package ua.pp.viktor.chapter05;

public class Exercise20 {

	public static void main(String[] args) {
		System.out.println("Degree\t\t  Sin\t\t  Cos");
		for (int degree = 0; degree <= 360; degree += 10) {
			System.out.printf("%d\t\t%7.4f\t\t%7.4f\n", 
					degree, Math.sin(Math.toRadians(degree)), Math.cos(Math.toRadians(degree)));
		}
	}

}
