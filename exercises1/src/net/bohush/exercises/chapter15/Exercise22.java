package net.bohush.exercises.chapter15;

public class Exercise22 {

	public static void main(String[] args) {
		Rational17 r1 = new Rational17();
		for (int i = 1; i < 100; i++) {
			r1 = r1.add(new Rational17(i, i +1));			
		}
		System.out.println(r1);
		System.out.println(r1.doubleValue());
	}

}
