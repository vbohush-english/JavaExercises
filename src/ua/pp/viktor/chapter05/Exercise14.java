package ua.pp.viktor.chapter05;

public class Exercise14 {

	public static void main(String[] args) {
		System.out.println("i\t   m(i)");
		System.out.println("---------------");
		for (int i = 1; i < 1000; i += 100) {
			System.out.printf("%d\t%7.4f\n", i, getPi(i));	
		}
	}
	
	
	public static double getPi(double count) {
		double pi = 0;
		for (int i = 1; i <= count; i ++) {
			pi +=  Math.pow(-1, i + 1)/ (2 * i - 1);
		}
		return 4 * pi;
	}

}
